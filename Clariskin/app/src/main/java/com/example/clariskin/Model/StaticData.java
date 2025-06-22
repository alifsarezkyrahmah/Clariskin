package com.example.clariskin.Model;

import com.example.clariskin.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticData {

    public static List<Product> productList = new ArrayList<>();
    public static List<Ingredient> ingredientList = new ArrayList<>();

    public static void initData() {
        // Log untuk melacak kapan initData dipanggil
        android.util.Log.d("StaticData", "initData() called. productList size: " + productList.size() + ", ingredientList size: " + ingredientList.size());

        // HANYA INISIALISASI JIKA LIST KOSONG
        if (!productList.isEmpty() || !ingredientList.isEmpty()) {
            android.util.Log.d("StaticData", "Data already initialized. Skipping initData().");
            return;
        }

        productList.clear(); // Pastikan bersih sebelum mengisi
        ingredientList.clear(); // Pastikan bersih sebelum mengisi
        android.util.Log.d("StaticData", "Lists cleared. Starting data population.");


        // ================================
        // ========== Ingredients (Definisikan dulu agar ID-nya bisa dipakai di Produk) =========
        // ================================
        Ingredient niacinamide = new Ingredient(1, "Niacinamide (Vitamin B3)",
                "Vitamin B3 yang membantu mengontrol produksi minyak, mengecilkan tampilan pori-pori, meredakan kemerahan, dan memperbaiki tekstur kulit secara keseluruhan.",
                "Gunakan di pagi dan malam hari. Mulai dengan konsentrasi rendah (2-5%) jika baru pertama kali, lalu tingkatkan.",
                "Hyaluronic Acid, Ceramides, Peptides, Alpha Arbutin, Salicylic Acid (pisahkan waktu)",
                "Vitamin C (L-Ascorbic Acid murni, sebaiknya pisahkan waktu untuk potensi iritasi atau degradasi)",
                "Oily, Combination, Acne-prone, Sensitive, Normal",
                "Large Pores, Excess Oil, Uneven Texture, Acne, Redness, Dullness",
                R.drawable.nia); // Asumsi Anda punya drawable ini

        Ingredient salicylicAcid = new Ingredient(2, "Salicylic Acid (BHA)",
                "Asam beta-hidroksi (BHA) larut minyak, efektif menembus pori-pori, membersihkan sumbatan, dan mengurangi jerawat.",
                "Gunakan 2-3 kali seminggu, biasanya malam hari. Mulai dengan konsentrasi rendah.",
                "Hyaluronic Acid, Ceramides, Niacinamide (gunakan dengan hati-hati/beda waktu)",
                "Retinol, Benzoyl Peroxide (dapat menyebabkan iritasi berlebihan jika digabung bersamaan)",
                "Oily, Acne-prone, Combination",
                "Acne, Blackheads, Whiteheads, Large Pores, Excess Oil",
                R.drawable.salycilic); // Asumsi Anda punya drawable ini

        Ingredient hyaluronicAcid = new Ingredient(3, "Hyaluronic Acid (HA)",
                "Humektan yang menarik dan menahan kelembapan hingga 1000x beratnya sendiri, membuat kulit terasa kenyal dan lembap.",
                "Gunakan di kulit lembap setelah membersihkan wajah dan sebelum serum/moisturizer lainnya. Cocok untuk penggunaan pagi dan malam.",
                "Semua bahan aktif lainnya (Vitamin C, Retinol, Niacinamide, AHA/BHA)",
                "Tidak ada kombinasi buruk yang signifikan.",
                "All Skin Types, Dry, Dehydrated, Sensitive",
                "Dryness, Dehydration, Fine Lines, Dullness",
                R.drawable.hya); // Asumsi Anda punya drawable ini

        Ingredient ceramides = new Ingredient(4, "Ceramides",
                "Lipid alami yang merupakan komponen kunci dari skin barrier kulit. Membantu mengunci kelembapan dan melindungi dari iritan.",
                "Gunakan setiap hari, pagi dan malam, sebagai bagian dari pelembap atau serum. Sangat cocok untuk kulit kering atau sensitif.",
                "Semua bahan aktif lainnya (Niacinamide, Hyaluronic Acid, Peptides, Retinol, Vitamin C)",
                "Tidak ada kombinasi buruk yang signifikan.",
                "All Skin Types, Dry, Sensitive, Damaged Barrier",
                "Dryness, Redness, Itchiness, Damaged Barrier",
                R.drawable.cera); // Asumsi Anda punya drawable ini

        Ingredient alphaArbutin = new Ingredient(5, "Alpha Arbutin",
                "Turunan hydroquinone yang lebih stabil dan aman, bekerja menghambat produksi melanin untuk mencerahkan noda hitam.",
                "Gunakan di pagi dan/atau malam hari. Selalu gunakan sunscreen di pagi hari saat menggunakan bahan pencerah.",
                "Niacinamide, Hyaluronic Acid, Vitamin C",
                "Tidak ada kombinasi buruk yang signifikan, namun hindari penggunaan berlebihan dengan bahan pencerah kuat lainnya jika kulit sensitif.",
                "All Skin Types",
                "Hyperpigmentation, Dark Spots, Uneven Tone, Melasma",
                R.drawable.alpha); // Asumsi Anda punya drawable ini

        Ingredient vitaminC = new Ingredient(6, "Vitamin C (L-Ascorbic Acid)",
                "Antioksidan kuat yang melindungi kulit dari radikal bebas, mencerahkan warna kulit, dan merangsang produksi kolagen.",
                "Gunakan di pagi hari untuk perlindungan antioksidan, setelah membersihkan dan sebelum moisturizer/sunscreen.",
                "Hyaluronic Acid, Vitamin E, Ferulic Acid",
                "Niacinamide (L-Ascorbic Acid murni dan Niacinamide sebaiknya dipisah waktu)",
                "All Skin Types, Dull, Aging, Sun-Damaged",
                "Dullness, Uneven Tone, Fine Lines, Sun Damage, Dark Spots",
                R.drawable.vitc); // Asumsi Anda punya drawable ini

        Ingredient retinol = new Ingredient(7, "Retinol",
                "Bentuk Vitamin A yang efektif dalam mengurangi tanda-tanda penuaan, memperbaiki tekstur kulit, dan mengatasi jerawat.",
                "Gunakan di malam hari, dimulai dengan konsentrasi rendah 1-2 kali seminggu, tingkatkan bertahap. Selalu gunakan sunscreen di pagi hari.",
                "Hyaluronic Acid, Ceramides, Peptides",
                "AHA/BHA (Glycolic Acid, Salicylic Acid), Benzoyl Peroxide (dapat menyebabkan iritasi berlebihan jika digabung bersamaan)",
                "Normal, Oily, Combination, Mature, Acne-prone (setelah adaptasi)",
                "Fine Lines, Wrinkles, Acne, Uneven Texture, Hyperpigmentation",
                R.drawable.retinol); // Asumsi Anda punya drawable ini

        // Tambahkan semua ingredients ke list DULU
        ingredientList.addAll(Arrays.asList(
                niacinamide, salicylicAcid, hyaluronicAcid,
                ceramides, alphaArbutin, vitaminC, retinol
        ));
        android.util.Log.d("StaticData", "Ingredients populated. ingredientList size: " + ingredientList.size());


        // ================================
        // ========== Produk ==============
        // ================================
        Product p1 = new Product(1, "The Ordinary", "Niacinamide 10% + Zinc 1%",
                "Serum konsentrasi tinggi Niacinamide untuk mengurangi pori-pori, minyak berlebih, dan noda kulit.",
                "Oleskan sedikit ke seluruh wajah di pagi dan malam hari setelah membersihkan wajah.",
                "AM/PM", "Oily, Acne-prone, Combination",
                "Large Pores, Excess Oil, Acne, Blemishes",
                R.drawable.ordinary); // Asumsi Anda punya drawable ini
        p1.setRelatedIngredientIds(Arrays.asList(niacinamide.getId())); // Hanya Niacinamide, sesuai nama produknya
        // Untuk zinc, jika Anda memiliki ingredient zinc, tambahkan id-nya di sini
        // p1.setRelatedIngredientIds(Arrays.asList(niacinamide.getId(), zinc.getId()));

        Product p2 = new Product(2, "COSRX", "Low pH Good Morning Gel Cleanser",
                "Pembersih wajah ber pH rendah yang lembut, efektif membersihkan tanpa membuat kulit kering.",
                "Pijatkan dengan lembut pada wajah yang basah dan bilas bersih. Gunakan pagi dan malam.",
                "AM/PM", "All Skin Types, Sensitive",
                "Dryness, Irritation, Acne",
                R.drawable.cosrx); // Asumsi Anda punya drawable ini
        // COSRX cleanser ini TIDAK MENGANDUNG Salicylic Acid, dia pembersih gentle.
        // Jika Anda ingin produk yang mengandung SA, tambahkan produk lain atau ubah produk ini.
        p2.setRelatedIngredientIds(Arrays.asList(hyaluronicAcid.getId())); // Contoh: Pembersih seringkali mengandung HA

        Product p3 = new Product(3, "SOMETHINC", "Niacinamide + Beet Serum 5%",
                "Serum pencerah dengan kombinasi Niacinamide dan ekstrak Beet untuk kulit lebih cerah dan sehat.",
                "Gunakan 2-3 tetes setelah toner, pijat lembut hingga meresap. Lanjutkan dengan moisturizer.",
                "AM/PM", "All Skin Types",
                "Dullness, Uneven Tone, Dark Spots",
                R.drawable.someth); // Asumsi Anda punya drawable ini
        p3.setRelatedIngredientIds(Arrays.asList(niacinamide.getId(), hyaluronicAcid.getId()));

        Product p4 = new Product(4, "Skintific", "5X Ceramide Barrier Repair Moisture Gel",
                "Moisturizer gel ringan dengan 5 jenis Ceramide untuk memperkuat skin barrier.",
                "Oleskan secukupnya pada wajah setelah serum, pijat hingga meresap.",
                "AM/PM", "All Skin Types, Dry, Sensitive",
                "Dryness, Damaged Barrier, Redness",
                R.drawable.skintific); // Asumsi Anda punya drawable ini
        p4.setRelatedIngredientIds(Arrays.asList(ceramides.getId(), hyaluronicAcid.getId()));

        Product p5 = new Product(5, "Azarine", "Hydrasoothe Sunscreen Gel SPF45 PA++++",
                "Sunscreen bertekstur gel yang ringan dan mudah meresap, melindungi kulit dari sinar UV.",
                "Aplikasikan merata di wajah dan leher sebagai langkah terakhir skincare pagi.",
                "AM", "All Skin Types, Oily",
                "Sun Protection",
                R.drawable.azarine); // Asumsi Anda punya drawable ini
        p5.setRelatedIngredientIds(Arrays.asList(hyaluronicAcid.getId(), vitaminC.getId())); // Sunscreen sering punya Vit C

        Product p6 = new Product(6, "Avoskin", "Your Skin Bae Alpha Arbutin 3% + Grapeseed",
                "Serum pencerah dengan Alpha Arbutin untuk mengatasi hiperpigmentasi dan noda hitam.",
                "Gunakan 2-3 tetes pada wajah di pagi dan malam hari. Lanjutkan dengan moisturizer.",
                "AM/PM", "All Skin Types",
                "Hyperpigmentation, Dark Spots, Uneven Tone",
                R.drawable.avoskin_ampoule); // Asumsi Anda punya drawable ini
        p6.setRelatedIngredientIds(Arrays.asList(alphaArbutin.getId(), hyaluronicAcid.getId()));

        Product p7 = new Product(7, "Trueve", "Trueve Luminous Dark Spot Corrector Serum",
                "Serum dengan perpaduan Niacinamide dan Chromabright untuk menyamarkan noda hitam.",
                "Aplikasikan 2-3 tetes pada area yang memiliki noda hitam, ratakan ke seluruh wajah.",
                "AM/PM", "All Skin Types",
                "Dark Spots, Uneven Tone",
                R.drawable.trueve); // Asumsi Anda punya drawable ini
        p7.setRelatedIngredientIds(Arrays.asList(niacinamide.getId(), vitaminC.getId()));

        p1.setDoNotUseWithIngredientIds(Arrays.asList(vitaminC.getId())); // Tidak disarankan dengan Vitamin C
        p1.setGoodFor("Mengurangi minyak dan mengecilkan pori dalam 2-4 minggu.");

        p2.setDoNotUseWithIngredientIds(new ArrayList<>()); // Aman dengan semua
        p2.setGoodFor("Membersihkan kulit dengan pH seimbang, cocok untuk penggunaan harian.");

        p3.setDoNotUseWithIngredientIds(Arrays.asList(vitaminC.getId())); // Kombinasi bisa menyebabkan iritasi
        p3.setGoodFor("Mencerahkan kulit secara bertahap dalam 3-6 minggu.");

        p4.setDoNotUseWithIngredientIds(new ArrayList<>()); // Aman dengan semua
        p4.setGoodFor("Memperkuat skin barrier dalam 1-2 minggu.");

        p5.setDoNotUseWithIngredientIds(new ArrayList<>()); // Aman dengan semua
        p5.setGoodFor("Melindungi kulit dari sinar UV, efektif digunakan setiap hari.");

        p6.setDoNotUseWithIngredientIds(new ArrayList<>()); // Tidak ada larangan kuat
        p6.setGoodFor("Mengurangi noda hitam dan meratakan warna kulit dalam 4 minggu.");

        p7.setDoNotUseWithIngredientIds(Arrays.asList(retinol.getId())); // Retinol bisa membuat kulit sensitif
        p7.setGoodFor("Mengurangi noda gelap dan menyeimbangkan warna kulit secara bertahap.");

        // Tambahkan semua produk ke list DULU
        productList.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
        android.util.Log.d("StaticData", "Products populated. productList size: " + productList.size());


        // --- Sekarang, update relatedProductIds untuk Ingredients ---
        // PENTING: Panggil ini SETELAH productList diisi dan semua objek Product memiliki ID yang benar.
        //           Jangan langsung menggunakan p1, p2, dll. di atas tanpa mereka ditambahkan ke productList.
        //           Lebih aman mengambil dari productList jika p1, p2, dll. adalah variabel lokal.
        //           Tapi karena Anda set ID-nya, harusnya aman.
        niacinamide.setRelatedProductIds(Arrays.asList(p1.getId(), p3.getId(), p7.getId()));
        salicylicAcid.setRelatedProductIds(Arrays.asList(p1.getId())); // p1 Niacinamide + Zinc, Zinc kadang dikaitkan dgn SA
        hyaluronicAcid.setRelatedProductIds(Arrays.asList(p2.getId(), p3.getId(), p4.getId(), p5.getId(), p6.getId()));
        ceramides.setRelatedProductIds(Arrays.asList(p4.getId()));
        alphaArbutin.setRelatedProductIds(Arrays.asList(p6.getId()));
        vitaminC.setRelatedProductIds(Arrays.asList(p5.getId(), p7.getId()));
        retinol.setRelatedProductIds(Arrays.asList()); // Belum ada produk dummy dengan Retinol

        android.util.Log.d("StaticData", "Related Product IDs set for Ingredients.");
    }


    public static Ingredient getIngredientById(int id) {
        for (Ingredient i : ingredientList) {
            if (i.getId() == id) return i;
        }
        return null;
    }

    public static Product getProductById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}