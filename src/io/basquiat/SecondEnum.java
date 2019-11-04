package io.basquiat;

import java.util.Arrays;

/**
 * 
 * 
 * 마치 클래스처럼
 * created by basquiat
 *
 *
 */
public enum SecondEnum {
	
	LEVEL_ONE("one", 0),
	
	LEVEL_TWO("two", 1),
	
	LEVEL_THREE("three", 2);
	
	/** enum code */
	public String code;
	
	/** enum index */
	public int index;
	
	/** String, init type constructor */
	SecondEnum(String code, int index) {
		this.code = code;
		this.index = index;
	}

	/**
	 * get Enum Object from code
	 * @param code
	 * @return SecondEnum
	 */
	public static SecondEnum fromString(String code) {
		return Arrays.asList(SecondEnum.values())
					 .stream()
					 .filter( secondEnum -> secondEnum.code.equalsIgnoreCase(code) )
					 .map(secondEnum -> secondEnum)
					 .findFirst().orElse(null);
    }
	
}
