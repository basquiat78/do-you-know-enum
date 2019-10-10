package io.basquiat;

import java.math.BigDecimal;

public class MAIN {

	public static void main(String[] args) {

		/*
		System.out.println(InitEnum.LEVEL_ONE.name());
		System.out.println(InitEnum.LEVEL_TWO.name());
		System.out.println(InitEnum.LEVEL_THREE.name());
		
		System.out.println(InitEnum.LEVEL_ONE.ordinal());
		System.out.println(InitEnum.LEVEL_TWO.ordinal());
		System.out.println(InitEnum.LEVEL_THREE.ordinal());
		
		Stream.of(InitEnum.values()).forEach(initEnum -> System.out.println(initEnum.name()));
		
		InitEnum level = InitEnum.LEVEL_ONE;
		
		switch(level) {
			case LEVEL_ONE:
				System.out.println("level 1");
				break;
			case LEVEL_TWO:
				System.out.println("level 2");
				break;
			case LEVEL_THREE:
				System.out.println("level 3");
				break;
			default:
				System.out.println("nothing");
				break;
		
		}
		
		System.out.println(SecondEnum.LEVEL_ONE.code);
		System.out.println(SecondEnum.LEVEL_ONE.index);
		System.out.println(SecondEnum.fromString("one"));
		 */
		
		BigDecimal gold = BigDecimal.valueOf(100);
		System.out.println(ThirdEnum.LEVEL_ONE.rewardGold(gold));
		System.out.println(ThirdEnum.fromString("one").rewardGold(gold));
		System.out.println(ThirdEnum.LEVEL_TWO.rewardGold(gold));
		System.out.println(ThirdEnum.fromString("two").rewardGold(gold));
		System.out.println(ThirdEnum.LEVEL_THREE.rewardGold(gold));
		System.out.println(ThirdEnum.fromString("three").rewardGold(gold));
		
	}

}
