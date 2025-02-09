package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SuitRepository {
    private List<SuperheroSuit> suits;
    private Map<String, Integer> repairCounts;
    private final String fileName = "suits.csv";
    
    public SuitRepository() {
        suits = new ArrayList<>();
        repairCounts = new HashMap<>();

        // กำหนดประเภทและค่าเริ่มต้นสำหรับการนับการซ่อมแซมในแต่ละการ Run 
        repairCounts.put("ชุดทรงพลัง", 0);
        repairCounts.put("ชุดลอบเร้น", 0);
        repairCounts.put("ชุดปกปิดตัวตน", 0);
        loadData();
    }
    
    public List<SuperheroSuit> getSuits() {
        return suits;
    }
    
    // Find suit from code
    public SuperheroSuit getSuitByCode(String code) {
        for (SuperheroSuit suit : suits) {
            if(suit.getCode().equals(code)) {
                return suit;
            }
        }
        return null;
    }
    
    // Update when repiared and save into CSV
    public void updateSuit(SuperheroSuit suit) {
        saveData();
    }
    
    // Count Repiar ++
    public void incrementRepairCount(String type) {
        repairCounts.put(type, repairCounts.get(type) + 1);
    }
    
    public Map<String, Integer> getRepairCounts() {
        return repairCounts;
    }
    
    // Load Data in CSV if not have will create new
    private void loadData() {
        File file = new File(fileName);
        if (!file.exists()) {
            generateSampleData();
            saveData();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    if (firstLine && line.startsWith("code")) {
                        firstLine = false;
                        continue;
                    }
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String code = parts[0].trim();
                        String type = parts[1].trim();
                        int durability = Integer.parseInt(parts[2].trim());
                        suits.add(new SuperheroSuit(code, type, durability));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Initial Data
    private void generateSampleData() {
        String[] types = {"ชุดทรงพลัง", "ชุดลอบเร้น", "ชุดปกปิดตัวตน"};
        Set<String> usedCodes = new HashSet<>();
        Random rand = new Random();
        
        for (String type : types) {
            for (int i = 0; i < 10; i++) {
                String code = generateUniqueCode(usedCodes, rand);
                int durability = 0;
                if (type.equals("ชุดทรงพลัง")) {
                    durability = rand.nextInt(101);  
                } else if (type.equals("ชุดลอบเร้น")) {
                    durability = rand.nextInt(101);  
                } else if (type.equals("ชุดปกปิดตัวตน")) {
                    do {
                        durability = rand.nextInt(101); 
                    } while (durability % 10 == 3 || durability % 10 == 7); // Check ลงท้าย 3 กับ 7
                }
                suits.add(new SuperheroSuit(code, type, durability));
            }
        }

        // Create new when at least 50 row
        int totalNeeded = 50 - suits.size();
        for (int i = 0; i < totalNeeded; i++) {
            String type = types[rand.nextInt(types.length)];
            String code = generateUniqueCode(usedCodes, rand);
            int durability = 0;
            if (type.equals("ชุดทรงพลัง")) {
                durability = rand.nextInt(31) + 70;
            } else if (type.equals("ชุดลอบเร้น")) {
                durability = rand.nextInt(51) + 50;
            } else if (type.equals("ชุดปกปิดตัวตน")) {
                do {
                    durability = rand.nextInt(101);
                } while (durability % 10 == 3 || durability % 10 == 7);
            }
            suits.add(new SuperheroSuit(code, type, durability));
        }
    }
    
    // Create code 6 digits and the first is not 0
    private String generateUniqueCode(Set<String> usedCodes, Random rand) {
        String code;
        do {
            int num = rand.nextInt(900000) + 100000;  
            code = String.valueOf(num);
        } while (usedCodes.contains(code));
        usedCodes.add(code);
        return code;
    }
    
    // Save into CSV
    private void saveData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            // เขียน header
            pw.println("code,type,durability");
            for (SuperheroSuit suit : suits) {
                pw.println(suit.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
