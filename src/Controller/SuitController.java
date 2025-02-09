package Controller;

import Model.SuitRepository;
import Model.SuperheroSuit;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuitController {
    private SuitRepository repository;
    private MainView view;
    
    public SuitController(SuitRepository repository, MainView view) {
        this.repository = repository;
        this.view = view;
        initController();
    }
    
    private void initController() {
        view.getInputPanel().getCheckButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSuit();
            }
        });
        view.getStatusPanel().getRepairButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairSuit();
            }
        });
    }
    
    private void checkSuit() {
        String code = view.getInputPanel().getSuitCodeInput().getText().trim();
        if (!code.matches("^[1-9]\\d{5}$")) {
            view.getStatusPanel().showMessage("รหัสชุดไม่ถูกต้อง (ต้องเป็นตัวเลข 6 หลัก ตัวแรกไม่เป็น 0)");
            return;
        }
        SuperheroSuit suit = repository.getSuitByCode(code);
        if (suit == null) {
            view.getStatusPanel().showMessage("ไม่พบรหัสชุดในฐานข้อมูล");
            return;
        }
        view.getDisplayPanel().displaySuitDetails(suit);
        if (!suit.isDurabilityValid()) {
            view.getStatusPanel().showRepairOption(true);
            view.getStatusPanel().showMessage("ชุดยังไม่ผ่านการตรวจสอบความทนทาน (ต้องซ่อมแซม)");
        } else {
            view.getStatusPanel().showRepairOption(false);
            view.getStatusPanel().showMessage("ชุดนี้ผ่านการตรวจสอบความทนทานแล้ว (ซ่อมแซมแล้ว)");
        }
    }
    
    private void repairSuit() {
        String code = view.getInputPanel().getSuitCodeInput().getText().trim();
        SuperheroSuit suit = repository.getSuitByCode(code);
        if (suit == null) {
            view.getStatusPanel().showMessage("ไม่พบรหัสชุดในฐานข้อมูล");
            return;
        }
        suit.repair();
        repository.updateSuit(suit);
        repository.incrementRepairCount(suit.getType());
        view.getDisplayPanel().displaySuitDetails(suit);
        view.getStatusPanel().updateRepairCounts(repository.getRepairCounts());
        if (suit.isDurabilityValid()) {
            view.getStatusPanel().showRepairOption(false);
            view.getStatusPanel().showMessage("ชุดนี้ผ่านการตรวจสอบความทนทานแล้ว (ซ่อมแซมแล้ว)");
        } else {
            view.getStatusPanel().showMessage("ชุดยังไม่ผ่านการตรวจสอบความทนทาน (ยังต้องซ่อมแซมอีก)");
        }
    }
}
