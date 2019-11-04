# do-you-know-enum
java8, function, express, enum


# 처음 ENUM을 알다.

아마도 신입시절이었던거 같다. 당시 같이 일하던 나이는 어렸지만 형, 동생 하던 나의 선임이 카테고리 코드를 디비로 관리하던 것을 enum이라는 클래스로 관리하기 위해 리팩토링 하던 시절이었다.

그 때 그 친구가 했던 말은 굳이 이런 것들을 DB에 관리하기엔 리소스가 크고 enum이라는 녀석을 이용해 코드 레벨에서 관리 할 수 있다는 것을 알려줬다.

# 기본적인 ENUM을 사용하다.

그리고 차후에 BPM과 관련된 업무를 하면서 enum을 활용해서 각 타입을 정의하기 시작했다.

가장 기본적인 형태는 다음과 같은 형식이었다.


```

package io.basquiat;

/**
 * 
 * 초기에 내가 알던 ENUM
 * created by basquiat
 *
 */
public enum InitEnum {
	
	LEVEL_ONE,
	
	LEVEL_TWO,
	
	LEVEL_THREE;

}


```

그냥 단순하게 어떤 코드값을 열거하는 가장 기본적인 방식이었다.

```

package io.basquiat;

import java.util.stream.Stream;

public class MAIN {

	public static void main(String[] args) {

		System.out.println(InitEnum.LEVEL_ONE.name());
		System.out.println(InitEnum.LEVEL_TWO.name());
		System.out.println(InitEnum.LEVEL_THREE.name());
		
		System.out.println(InitEnum.LEVEL_ONE.ordinal());
		System.out.println(InitEnum.LEVEL_TWO.ordinal());
		System.out.println(InitEnum.LEVEL_THREE.ordinal());
		
		Stream.of(InitEnum.values()).forEach(initEnum -> System.out.println(initEnum.name()));
		
		
	}

}


```

일반적으 위와 같은 방식으로 사용하게 된다. name()이라는 함수를 통해서 스트링 형식으로 값을 가져오거나 ordinal()이라는 함수를 통해서 나열된 인덱스를 가져온다거나 values()함수를 사용해 리스트로 값을 가져와 사용한다거나...

하지만 이것만으로는 무언가 enum을 써야 할 필요성을 크게 느끼지 못한다.

다만 상수를 정의해서 쓴다는 걔념이 있어서 switch문과 궁합이 잘 맞는다.

예를 들면 다음과 같이 정의해서 쓸수 있다.

```
package io.basquiat;

import java.util.stream.Stream;

public class MAIN {

	public static void main(String[] args) {
		
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
		
	}

}

```

일반적인 if문보다는 좀 더 제한적이고 엄격하게 사용할 수 있다는 것이다. (if처럼 무한대로 else if가 불가능하다.)

물론 enum에 정의한 값이 많아지면 이것도 결국 무의미하겠지만...

어째든 이것만으로는 뭔가 부족하다.

enum이 상수의 열거형이라고 하지만 엄밀히 말하면 클래스라는 것이다.

그래서 좀더 다양한 방식으로 바리에이션하기 시작한다.

# 마치 클래스처럼 

기존에는 name()와 ordinal()함수를 통해서 스트링 타입의 코드명과 인트 타입의 인덱스를 가져왔다. 

하지만 이것은 사용하는데 있어서 뭔가 명확성이 좀 떨어진다.

그래서 마치 클래스처럼 생성자와 열거형에 각 각의 타입을 명시적으로 작성해서 명확하게 사용하기 시작했다.

```
package io.basquiat;

/**
 * 
 * 마치 클래스처럼
 * created by basquiat
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
	
	/** String, int type constructor */
	SecondEnum(String code, int index) {
		this.code = code;
		this.index = index;
	}

}

```

위와 같이 마치 클래스처럼 코드명과 인덱스를 직접 지정하고 그에 따른 변수를 설정한다.

```

package io.basquiat;

public class MAIN {

	public static void main(String[] args) {

		System.out.println(SecondEnum.LEVEL_ONE.code);
		System.out.println(SecondEnum.LEVEL_ONE.index);
		
	}

}


```

사용하는데 있어서 변수명을 이용해 명확하게 어떤 값을 가져오는 지 알 수 있게 되었다.

여기에서 스트링 타입의 코드 값으로 enum을 가져오고 싶은 경우가 발생하기도 한다.

그래서 다음과 같이 values()함수를 이용해서 넘겨받은 코드값으로 해당 객체를 가져오는 코드를 추가 했다.

```
package io.basquiat;

import java.util.Arrays;

/**
 * 
 * 마치 클래스처럼
 * created by basquiat
 *
 */
public enum SecondEnum {
	
	LEVEL_ONE("one", 1),
	
	LEVEL_TWO("two", 2),
	
	LEVEL_THREE("three", 3);
	
	/** enum code */
	public String code;
	
	/** enum index */
	public int index;
	
	/** String, int type constructor */
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


```

다음과 같이 사용할 수 있다.

코드값으로 enum을 가져온다.

```

package io.basquiat;

public class MAIN {

	public static void main(String[] args) {
		System.out.println(SecondEnum.LEVEL_ONE.code);
		System.out.println(SecondEnum.LEVEL_ONE.index);
		System.out.println(SecondEnum.fromString("one"));
		
	}

}

```

# Effective Java 3/E

꾸준히 읽고 있던 조슈아 블로크의 이펙티브 자바의 3/E, 즉 자바 8에 맞춰진 개정판을 알게 되었고 읽기 시작하면서 영향을 받았던 부분중 하나가 enum에 대한 것이었다.

일단 이것을 적용하기 위해서는 java8의 functional Interface에 대한 이해가 필요하다.

그 중에 java.util.function 패키지에서 가장 많이 사용하는 것들을 제공 하고 있는데 그중에 

Interface		|   Function Descriptor  |  Abstract method

Function<T,R> 	|   (T) -> R 			 |  R apply(T t)


이것을 활용하는 것이다.

이 부분은 자바8에 대한 내용이기에 java8의 대표적인 9개 functional interface는 알고 가는 것이 좋다.

[Functional Interfaces in Java 8](https://www.baeldung.com/java-8-functional-interfaces)

뿐만 아니라 기본적인 자바8의 람다 표현식도 당연히 알아야 한다.

이제부터 무엇을 하고 싶은 건지 궁금할 수도 있고 아닐 수도 있지만 최종 목표는 enum을 통해서 캡슐화를 하는 데에 의의를 두고 싶다.

의외로 enum활용법을 잘 모르는 후배들도 있기도 하고 나 자신도 리마인드 차원에서 한번 정리를 해볼까 한다.

## 시나리오 

위에 만든 SecondEnum을 기준으로 이야기를 해보자.

가령 레벨에 따른 보상을 구현하고자 한다.

1. DB또는 무엇이 되었든 특정 퀘스트를 완료하게 되 현재 어떤 게임 유저의 레벨과 가지고 있는 골드에 대한 특정 배수만큼 곱한 골드를 지급한다.


## 전통적인 방식

```
	// 햔제 유저가 소지한 골
	int userGold = 1000;
	
	// 보상 골드 초
	int rewardGold = 0;
	
	// 현재 유저의 레벨
	SecondEnum level = SecondEnum.LEVEL_ONE;
	
	switch(level) {
		case LEVEL_ONE:
			rewardGold = userGold * 2;
			System.out.println("level 1");
			break;
		case LEVEL_TWO:
			rewardGold = userGold * 3;
			System.out.println("level 2");
			break;
		case LEVEL_THREE:
			rewardGold = basicGold * 4;
			System.out.println("level 3");
			break;
		default:
			rewardGold = userGold;
			System.out.println("nothing");
			break;
	
	}
	
	// 유저가 가진 골드와 레벨에 따라서 특정 퀘스트에 따른 보상 골드를 받는다.
	System.out.println(rewardGold);

```

위와 같은 방식으로 특정 퀘스트 완료에 따른 보상금을 지원한다든 유틸같은 것을 통해서 계산하는 메소드를 생성해서 사용하게 될 것이다.

예를 들면 다음과 같이 CommonUtils같은 곳에 계산을 하는 로직을 만들 수 도 있다.

아니면 if문으로 처리한다던가?

```

public class CommonUtils {

	/**
	 * reward gold
	 * 
	 * @param userGold (유저가 소지한 골드)
	 * @param level (유저의 레벨)
	 * @throws JsonProcessingException
	 */
	public static int rewardGold(int userGold, Level level ) {
		int rewardGold = 0;
		switch(level) {
			case LEVEL_ONE:
				rewardGold = userGold * 2;
				System.out.println("level 1");
				break;
			case LEVEL_TWO:
				rewardGold = userGold * 3;
				System.out.println("level 2");
				break;
			case LEVEL_THREE:
				rewardGold = basicGold * 4;
				System.out.println("level 3");
				break;
			default:
				rewardGold = userGold;
				System.out.println("nothing");
				break;
		
		}
		return rewardGold;
	}

}

```

하지만 위와 같은 방식은 좀 번거롭다. 만일 등급이 늘어나게 되면 기존의 로직에 if문 또는 case를 확장하면 되지만 귀찮다.

## 그럼 enum으로 어떻게 할 수 있는데?

아마도 위에서 java8의 functional interface를 언급했는지도 궁금해 할 수도 있다. (아닐 수도 있다.)

그렇다면 위에서 언급한 대표적인 9개의 functional interface중 Function은 뭐하는 넘이냐?

```
Function<T, R>

하나의 인자와 리턴타입을 가지며 그걸 제네릭으로 지정해줄수있다. 
이러한 이유로 타입파라미터(Type Parameter)가 2개다.

Function<String, Integer> f = string -> Integer.parseInt(string);
Integer result = f.apply("1");

```

위에 코드를 보면 스트링이라는 인자를 받아서 Integer형태로 바꾸는 로직이 있다.

물론 이 로직은 어떤 방식으로 사용하기 나름이다.

아무튼 이것을 abstract method를 통해서 호출해서 적용하게 할 수 있다.

자 이것을 우리는 enum에서 사용해 볼 생각이다.


```
package io.basquiat;

import java.util.Arrays;
import java.util.function.Function;
import java.math.BigDecimal;;

/**
 * 
 * java8을 최대한 활용해 보자
 * created by basquiat
 *
 */
public enum ThirdEnum {
	
	LEVEL_ONE("one", gold -> BigDecimal.valueOf(0.5).multiply(gold)),
	
	LEVEL_TWO("two", gold -> BigDecimal.valueOf(2).multiply(gold)),
	
	LEVEL_THREE("three", gold -> BigDecimal.valueOf(4).multiply(gold));
	
	/** enum code */
	public String code;
	
	/** functional interface를 활용하자 */
	private Function<BigDecimal, BigDecimal> expression;
	
	/** String, Function type constructor */
	ThirdEnum(String code, Function<BigDecimal, BigDecimal> expression) {
		this.code = code;
		this.expression = expression;
	}

	/**
	 * functional interface의 abstract Method를 통해서 계산을 처리해서 리턴하자.
	 * @param gold
	 * @return BigDecimal
	 */
	public BigDecimal rewardGold(BigDecimal gold) {
		return expression.apply(gold);
	}
	
	/**
	 * get Enum Object from code
	 * @param code
	 * @return ThirdEnum
	 */
	public static ThirdEnum fromString(String code) {
		return Arrays.asList(ThirdEnum.values())
					 .stream()
					 .filter( thirdEnum -> thirdEnum.code.equalsIgnoreCase(code) )
					 .map(thirdEnum -> thirdEnum)
					 .findFirst().orElse(null);
    }
	
}


```

변경된 코드이다.

특이한 점을 볼 수 있다.

```

LEVEL_ONE("one", gold -> BigDecimal.valueOf(0.5).multiply(gold)),

```

에서 두 번째 값을 보면 일반적인 상수가 아니라 하나의 표현식, 즉 functional interface라는 것 알 수 있다.

그러면 위에서 언급했던 Function<T, R>가 떠오를 것이다.

즉 gold라는 값이 들어오면 어떤 연산을 통해서 다시 리턴하리라는 것을!

그럼 내부 생성자를 통해서 

```
	/** String, Function type constructor */
	ThirdEnum(String code, Function<BigDecimal, BigDecimal> expression) {
		this.code = code;
		this.expression = expression;
	}
```

와 같이 코드를 짤 수 있다. 

지금은 Function에 두 인자값으로 BigDecimal을 대입했다. 

보면 알겠지만 BigDecimal타입의 값을 받아서 BigDecimal타입의 값을 리턴한다는 것을 알 수 있다. 

expression이라고 변수명을 지었지만 실제로는 열거한 상수의 2번째 functional interface를 받을 수 있게 만드는 것이다.


```
	/**
	 * functional interface의 abstract Method를 통해서 계산을 처리해서 리턴하자.
	 * @param gold
	 * @return BigDecimal
	 */
	public BigDecimal rewardGold(BigDecimal gold) {
		return expression.apply(gold);
	}
```

위 코드를 주목하자.

다시 한번 위에서 언급했던 functional interface중 Function의 정보를 보자.

Interface		|   Function Descriptor  |  Abstract method

Function<T,R> 	|   (T) -> R 			 |  R apply(T t)


감이 오는가??

```
package io.basquiat;

import java.math.BigDecimal;

public class MAIN {

	public static void main(String[] args) {
	
		// 현재 유저가 가지고 있는 골		
		BigDecimal gold = BigDecimal.valueOf(100);
		
		// 레벨에 따른 보상 골드를 가져와 보자.
		System.out.println(ThirdEnum.LEVEL_ONE.rewardGold(gold));
		System.out.println(ThirdEnum.fromString("one").rewardGold(gold));

		System.out.println(ThirdEnum.LEVEL_TWO.rewardGold(gold));
		System.out.println(ThirdEnum.fromString("two").rewardGold(gold));
		
		System.out.println(ThirdEnum.LEVEL_THREE.rewardGold(gold));
		System.out.println(ThirdEnum.fromString("three").rewardGold(gold));
		
	}

}

============== result ==============
50.0 <-- Level one
50.0 <-- Level one
200 <-- Level two
200 <-- Level two
400 <-- Level three
400 <-- Level three

```

레벨에 따른 값들을 반환하게 된다.

사실 별거 아니지만 이걸 실제 블록체인과 관련된 어플리케이션에서 유용하게 써먹었다.

어떻게 사용하냐는 분명 필요에 의해서 결정지어지겠지만 필요할 때 이런 방식이 있다는 것을 안다면 분명 유용하게 사용될 수 있는 부분이기도 하다.

P.S.

예를 들면 레벨에 대한 상수 정의가 늘어난다면 기존에 방식으로는 최소 2군데서 변경점이 생기게 된다.

해당 로직을 정의한 클래스 가령 CommonUtils같은 클래스에 추가된 상수에 대한 if문 또는 case를 정의해야 하고 추가된 코드를 확장하기 위한 enum클래스에도 변경점이 생긴다.

심한 경우 만일 필요할 때마다 저런 if문 또는 case문을 생성했다면 그 부분마다 일일히 찾아서 고쳐야 한다.

하지만 저렇게 enum에 정의를 하면 enum 단 하나만 수정하면 저 로직을 쓴 다른 곳에서는 전혀 신경쓸 필요가 없다.