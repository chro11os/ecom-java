package com.gabriel.ecomms;

import com.gabriel.ecomms.entity.CategoryData;
import com.gabriel.ecomms.entity.EcommerceData;
import com.gabriel.ecomms.entity.InventoryData;
import com.gabriel.ecomms.entity.ProductData;
import com.gabriel.ecomms.entity.StatusData;
import com.gabriel.ecomms.repository.CategoryDataRepository;
import com.gabriel.ecomms.repository.EcommerceDataRepository;
import com.gabriel.ecomms.repository.InventoryDataRepository;
import com.gabriel.ecomms.repository.ProductDataRepository;
import com.gabriel.ecomms.repository.StatusDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoryDataRepository categoryDataRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private StatusDataRepository statusDataRepository;
    @Autowired
    private InventoryDataRepository inventoryDataRepository;
    @Autowired
    private EcommerceDataRepository ecommerceDataRepository;

    @Override
    public void run(String... args) throws Exception {
        // Categories
        CategoryData albums = new CategoryData();
        albums.setName("Albums");
        categoryDataRepository.save(albums);

        CategoryData lightSticks = new CategoryData();
        lightSticks.setName("Light Sticks");
        categoryDataRepository.save(lightSticks);

        CategoryData apparel = new CategoryData();
        apparel.setName("Apparel");
        categoryDataRepository.save(apparel);

        // Products
        ProductData btsAlbum = new ProductData();
        btsAlbum.setName("BTS - Proof");
        productDataRepository.save(btsAlbum);

        ProductData blackpinkLightstick = new ProductData();
        blackpinkLightstick.setName("BLACKPINK Light Stick Ver.2");
        productDataRepository.save(blackpinkLightstick);

        ProductData twiceHoodie = new ProductData();
        twiceHoodie.setName("TWICE 4TH WORLD TOUR 'Ⅲ' HOODIE");
        productDataRepository.save(twiceHoodie);

        // Status
        StatusData inStock = new StatusData();
        inStock.setName("In Stock");
        statusDataRepository.save(inStock);

        StatusData outOfStock = new StatusData();
        outOfStock.setName("Out of Stock");
        statusDataRepository.save(outOfStock);

        // Inventory
        InventoryData mainWarehouse = new InventoryData();
        mainWarehouse.setName("Main Warehouse");
        inventoryDataRepository.save(mainWarehouse);

        // Ecommerce Items
        EcommerceData item1 = new EcommerceData();
        item1.setName("BTS - Proof (Standard Edition)");
        item1.setDescription("BTS's anthology album 'Proof'.");
        item1.setProductId(btsAlbum.getId());
        item1.setProductName(btsAlbum.getName());
        item1.setCategoryId(albums.getId());
        item1.setCategoryName(albums.getName());
        item1.setStatusId(inStock.getId());
        item1.setStatusName(inStock.getName());
        item1.setInventoryId(mainWarehouse.getId());
        item1.setInventoryName(mainWarehouse.getName());
        item1.setQuantityAvailable(100);
        item1.setPrice(55.99);
        item1.setImageUrl("https://via.placeholder.com/150/FFC0CB/000000?Text=BTS+Proof");
        ecommerceDataRepository.save(item1);

        EcommerceData item2 = new EcommerceData();
        item2.setName("BLACKPINK Official Light Stick Ver.2");
        item2.setDescription("The official light stick for BLINKs.");
        item2.setProductId(blackpinkLightstick.getId());
        item2.setProductName(blackpinkLightstick.getName());
        item2.setCategoryId(lightSticks.getId());
        item2.setCategoryName(lightSticks.getName());
        item2.setStatusId(inStock.getId());
        item2.setStatusName(inStock.getName());
        item2.setInventoryId(mainWarehouse.getId());
        item2.setInventoryName(mainWarehouse.getName());
        item2.setQuantityAvailable(50);
        item2.setPrice(45.00);
        item2.setImageUrl("https://via.placeholder.com/150/000000/FFFFFF?Text=BP+Lightstick");
        ecommerceDataRepository.save(item2);

        EcommerceData item3 = new EcommerceData();
        item3.setName("TWICE 4TH WORLD TOUR 'Ⅲ' HOODIE (M)");
        item3.setDescription("Official hoodie from TWICE's 4th world tour.");
        item3.setProductId(twiceHoodie.getId());
        item3.setProductName(twiceHoodie.getName());
        item3.setCategoryId(apparel.getId());
        item3.setCategoryName(apparel.getName());
        item3.setStatusId(outOfStock.getId());
        item3.setStatusName(outOfStock.getName());
        item3.setInventoryId(mainWarehouse.getId());
        item3.setInventoryName(mainWarehouse.getName());
        item3.setQuantityAvailable(0);
        item3.setPrice(70.00);
        item3.setImageUrl("https://via.placeholder.com/150/FFFFFF/000000?Text=TWICE+Hoodie");
        ecommerceDataRepository.save(item3);
    }
}
