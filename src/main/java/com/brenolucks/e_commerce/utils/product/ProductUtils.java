package com.brenolucks.e_commerce.utils.product;

import com.brenolucks.e_commerce.domain.enums.ProductType;
import com.brenolucks.e_commerce.service.product.ProductCategoryStrategy;
import com.brenolucks.e_commerce.service.product.strategy.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductUtils {
    public Map<ProductType, ProductCategoryStrategy> createProductCategoryStrategyMap() {
        return Map.ofEntries(
                Map.entry(ProductType.SMARTPHONE, new EletronicStrategy()),
                Map.entry(ProductType.LAPTOP, new EletronicStrategy()),
                Map.entry(ProductType.HEADPHONES, new EletronicStrategy()),
                Map.entry(ProductType.T_SHIRT, new ApparelStrategy()),
                Map.entry(ProductType.JEANS, new ApparelStrategy()),
                Map.entry(ProductType.SHOES, new ApparelStrategy()),
                Map.entry(ProductType.LAMP, new HomeDecorStrategy()),
                Map.entry(ProductType.CURTAIN, new HomeDecorStrategy()),
                Map.entry(ProductType.VASE, new HomeDecorStrategy()),
                Map.entry(ProductType.PERFUME, new PersonalCareStrategy()),
                Map.entry(ProductType.SKINCARE, new PersonalCareStrategy()),
                Map.entry(ProductType.HAIR_CARE, new PersonalCareStrategy()),
                Map.entry(ProductType.FICTION, new BooksStrategy()),
                Map.entry(ProductType.TECHNICAL, new BooksStrategy()),
                Map.entry(ProductType.CHILDREN, new BooksStrategy()),
                Map.entry(ProductType.MOVIE, new MediaStrategy()),
                Map.entry(ProductType.MUSIC, new MediaStrategy()),
                Map.entry(ProductType.VIDEO_GAME, new MediaStrategy()),
                Map.entry(ProductType.DOLL, new ToysStrategy()),
                Map.entry(ProductType.CAR, new ToysStrategy()),
                Map.entry(ProductType.PUZZLE, new ToysStrategy()),
                Map.entry(ProductType.BOARD_GAME, new GamesStrategy()),
                Map.entry(ProductType.CARD_GAME, new GamesStrategy()),
                Map.entry(ProductType.STRATEGY_GAME, new GamesStrategy()),
                Map.entry(ProductType.BALL, new SportsStrategy()),
                Map.entry(ProductType.RACKET, new SportsStrategy()),
                Map.entry(ProductType.EXERCISE_EQUIPMENT, new SportsStrategy()),
                Map.entry(ProductType.CAR_PART, new AutomotiveStrategy()),
                Map.entry(ProductType.TOOL, new AutomotiveStrategy()),
                Map.entry(ProductType.ACCESSORY, new AutomotiveStrategy()),
                Map.entry(ProductType.VITAMINS, new HealthStrategy()),
                Map.entry(ProductType.FIRST_AID, new HealthStrategy()),
                Map.entry(ProductType.MEDICAL_DEVICE, new HealthStrategy())
        );
    }
}
