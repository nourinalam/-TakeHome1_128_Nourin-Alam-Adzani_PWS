/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takehome.webservice.takehomewebservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class controller {
    @RequestMapping("/view")
    public String buyer(@RequestParam(value="nama") String nama,
                        @RequestParam(value="harga") String harga,
                        @RequestParam(value="item") String item,
                        @RequestParam(value="bayar") String bayar,
                        Model model){
        
        int hargabr, jumbr, totbr, diskon, persendisk, money;
        
        hargabr = Integer.parseInt(harga);
        jumbr = Integer.parseInt(item);
        money = Integer.parseInt(bayar);
        
        // Proses Hitung
        totbr = getTotal(hargabr, jumbr);
        diskon = getDiskon(hargabr, jumbr);
        persendisk = ketDiskon(hargabr, jumbr);
        String Kembalian = getKembalian(money, diskon);
        
        model.addAttribute("nama", nama); // nama barang
        model.addAttribute("harga","Rp " +hargabr); //harga satuan
        model.addAttribute("total", "Rp " + totbr); // total harga sebelum diskon
        model.addAttribute("totalakhir", "Rp " + diskon ); // total harga setelah diskon
        model.addAttribute("kembalian", Kembalian); // kembalian
        model.addAttribute("bayar","Rp " +  money); // Bayar
        model.addAttribute("jumbarang", jumbr); //jumlah barang
        model.addAttribute("jumdiskon", persendisk + " %"); //jumlah diskon
        
        
        return "view";
    }
    
    public int getTotal(int harga, int item){
        int total = harga * item;
        
        return total;
    }
    
    // Hitung Diskon
    public int getDiskon(int harga, int item){
        int total = harga * item;
        int diskon;
        
        // Potongan 0 %
        if(total <= 10000){
            diskon = total - (total * 0);
        }
        // Diskon 5 %
        else if((total > 10000) && (total <= 50000)){
            diskon = total - (total * 5/100);
        }
        // Diskon 10 %
        else if( (total > 50000) && (total <= 100000)){
            diskon = total - (total * 10/100);
        }
        // Diskon 15 %
        else{
            diskon = total - (total * 15/100);
        }
        
        return diskon;
    }

//  Keterangan Diskon
    public int ketDiskon(int harga, int item){
        int total = harga * item;
        int diskon;
        
        // Potongan 0%
        if(total <= 10000){
            diskon = 0;
        }
        // Diskon 5%
        else if((total > 10000) && (total <= 50000)){
            diskon = 5;
        }
        // Diskon 10%
        else if((total > 50000) && (total <= 100000)){
            diskon = 10;
        }
        // Diskon 15%
        else{
            diskon = 15;
        }
        
        return diskon;
    }
    
    public String getKembalian(int uangBayar, int getPotongan){
        int kembalian = uangBayar- getPotongan;
        
        String hasil;
        
        if(uangBayar >= getPotongan){
            hasil = "Rp " + kembalian;
        }
        else{
            hasil = "Uang Yang Anda Bayarkan Kurang";
        }
        
        return hasil;
        
        
    }
    
}
    

