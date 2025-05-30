/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aydinsandikcimenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author aydin
 */
public class GameManager {

    private int level;
    private int puan;
    private int konum;
    private int zar;
    private String isim;
    private String hazine;

    public GameManager() {
        this.level = 1;
        this.puan = 0;
        this.konum = 0;
        this.zar = 0;
        this.isim = "";
        this.hazine = "";
    }

    
    MyLinkedList list;//= new MyLinkedList();
    MyNode currentNode;

    public void zarAt() {
        this.zar = (int) ((Math.random() * 6) + 1);
        ilerle();
    }

    public void ilerle() {
        for (int i = 0; i < zar; i++) {
            if (currentNode == null) {
                currentNode = list.getHead(); // ilk hamlede head'e gel
            } else if (currentNode.next != null) {
                currentNode = currentNode.next;
            } else {
                break;
            }
            konum++;
        }

        if (!bitisEkrani()) {
            hazineAc();
        } else {
            ekraniDegis();
        }
    }

    public void hazineAc() {
        String sonuc = "";
        if (currentNode == null) {
            return;
        }
        int hazine = currentNode.data;

        if (hazine == 0) {
            sonuc = "spot boş";
        } else if (hazine == 1) {
            sonuc = "10 puan kazandın";
            puanEkle(10);
        } else if (hazine == 2) {
            sonuc = "10 puan kaybettin";
            puanEkle(-10);
        } else if (hazine == 3) {
            sonuc = "3 adım ileri git";
            ileriGeriGit(3);
        } else if (hazine == 4) {
            sonuc = "2 adım geri git";
            ileriGeriGit(-2);
        }

        this.hazine = sonuc;
    }

    public void puanEkle(int puan) {
        this.puan += puan;
    }

    public void ileriGeriGit(int adim) {
        if (adim > 0) {
            for (int i = 0; i < adim && currentNode != null && currentNode.next != null; i++) {
                currentNode = currentNode.next;
                konum++;

            }
            JOptionPane.showMessageDialog(
                    null,
                    adim + " adım ileri git geldi \n Yeni Konumun " + konum,
                    "Şanslısın",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (!bitisEkrani()) {
                hazineAc();
            } else {
                ekraniDegis();
            }
            hazineAc();
        } else {
            for (int i = 0; i < Math.abs(adim) && konum > -1; i++) {
                currentNode = findPrevious(currentNode);
                konum--;
            }
            JOptionPane.showMessageDialog(
                    null,
                    (-adim) + " adım geri git geldi \n Yeni Konumun " + konum,
                    "Şansına küs",
                    JOptionPane.INFORMATION_MESSAGE
            );
            hazineAc();
        }
    }

    private MyNode findPrevious(MyNode node) {
        MyNode temp = list.getHead();
        if (temp == node) {
            return temp;
        }
        while (temp != null && temp.next != node) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean bitisEkrani() {
        boolean bitti = false;
        if (konum >= 30) {
            bitti = true;
            konum = 30;
        }
        return bitti;
    }

    public void dosyayaYaz(String isim, int level, int puan) {
        try (FileWriter yazici = new FileWriter("score.txt", true)) {
            yazici.write(isim + "," + "level" + level + "," + puan + "\n");
            System.out.println("Basariyla dosyaya yazildi.");
        } catch (IOException e) {
            System.out.println("Dosyaya yazarken hata oluştu: " + e.getMessage());
        }
    }

    public BinarySearchTree dosyadanOku() {
        BinarySearchTree bst = new BinarySearchTree();

        try (BufferedReader veriOkuyucu = new BufferedReader(new FileReader("score.txt"))) {
            String veri;
            while ((veri = veriOkuyucu.readLine()) != null) {
                String[] parcalar = veri.split(",");
                if (parcalar.length == 3 && parcalar[0].equals(isim)) {
                    String level = parcalar[1];
                    int puan = Integer.parseInt(parcalar[2]);
                    bst.insert(isim, level, puan);
                }
            }
            System.out.println("İsim '" + isim + "' için veriler BST'ye başarıyla aktarıldı.");
        } catch (IOException e) {
            System.out.println("Dosyayı okurken hata oluştu: " + e.getMessage());
        }

        return bst;
    }

    public void haritaOlustur() {
        list = new MyLinkedList();
        if (level == 1) {
            for (int i = 0; i < 30; i++) {
                int yerlestir = (int) (Math.random() * 3);
                list.add(yerlestir);
            }
        } else if (level == 2) {
            for (int i = 0; i < 30; i++) {
                int yerlestir = (int) (Math.random() * 5);
                list.add(yerlestir);
            }
        }
        //currentNode = list.getHead(); // Başlangıç
    }

    public void ekraniDegis() {
        dosyayaYaz(this.isim, this.level, this.puan);
        if (this.level == 1) {
            int secim = JOptionPane.showConfirmDialog(
                    null,
                    "Tebrikler! Aşama bitti.\nSkorlarınız kaydedilmiştir.\nİkinci aşamaya geçmek ister misiniz?",
                    "Aşama Tamamlandı",
                    JOptionPane.YES_NO_OPTION
            );

            if (secim == JOptionPane.YES_OPTION) {
                AydinSandikciIkinciEtap ie = new AydinSandikciIkinciEtap(this.isim);
                ie.setVisible(true);
            } else {
                AydinSandikciMenu m = new AydinSandikciMenu();
                m.setVisible(true);
            }
        } else if (level == 2) {
            JOptionPane.showMessageDialog(
                    null,
                    "Oyun bitmiştir, tebrikler!\nSkorlarınız kaydedilmiştir.",
                    "Oyun Bitti",
                    JOptionPane.INFORMATION_MESSAGE
            );

            AydinSandikciMenu m = new AydinSandikciMenu();
            m.setVisible(true);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public int getKonum() {
        return konum;
    }

    public void setKonum(int konum) {
        this.konum = konum;
    }

    public int getZar() {
        return zar;
    }

    public void setZar(int zar) {
        this.zar = zar;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getHazine() {
        return hazine;
    }

    public void setHazine(String hazine) {
        this.hazine = hazine;
    }

}
