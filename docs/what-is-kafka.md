# 📦 kafa란? 우체국이다 ?!
수많은 `택배`를 안전하게 순차적으로 저장해 두는 `우체국`입니다.  
`보내는 사람`이 `택배`를 맡기면, `우체국`은 이를 분실 위험 없이 `목적지` 별로 차곡차곡 쌓아둡니다.  
`택배를 찾는 사람`은 `택배`를 가져갈 수 있을때 `택배`를 찾을 수 있을때 `택배`를 가져갑니다.
> 💡 kafka로 대입해보자!  
> 수많은 `message`를 안전하게 순차적으로 저장해 두는 `kafka`입니다.  
> `producer`이 `message`를 맡기면, `kafka`은 이를 분실 위험 없이 `topic` 별로 차곡차곡 쌓아둡니다.  
> `consumer`은 `message`를 가져갈 수 있을때 `message`를 찾을 수 있을때 `message`를 가져갑니다.

## 📌 kafka의 구성요소
***`보내는 사람` = `producer`***  
- 택배를 목적지에 맡깁니다.  

***`목적지` = `topic`***   
- 택배를 저장하는 공간이다.  

***`서랍` = `partition`***   
- 같은 목적지를 가진 택배를 서랍에 잘 분리한다.  

***`관리직원` = `broker`***   
- 택배를 관리하는 사람이다.  

***`택배를 찾는 사람` = `consumer`***   
- 목적지에 맞는 택배를 찾는 사람이다.  
- 택배를 찾을 수 있을 때 찾으러 온다.   

***`택배번호` = `offset`***  
- 택배가 어디에 위치해 있는지 알려주는 번호이다.  

***`택배를 찾는 사람의 가족` = `consumer-group`***  
- 택배를 찾는 사람의 가족들이다.  

## 🔄 kafka의 흐름
1️⃣ `producer`가 `topic`에 `message`를 맡긴다.  
2️⃣ `broker`가 `topic`의 `partition`에 순사척으로 저장한다.  
3️⃣ `consumer`가 `topic`에 `message`를 가져간다.
> 💡 한번 가져간 `message` 다른 `consumer`에서도 소비가 가능합니다.

> 💡 한꺼번에 여러개의 `message`도 가져갈 수 있습니다! 

## 🫢 kafka가 인기있는 이유!!!
### 1️⃣ 데이터를 깔끔하게 분류하고, 관심사를 분리할 수 있다.
Kafka의 topic 구조 덕분에 `주문`, `결제`, `알림`, `로그` 등 서로 다른 데이터 흐름을 각각의 `topic`으로 깔끔하게 분리할 수 있습니다.  
각각의 팀, 서비스는 자신에게 필요한 topic만 사용하면 되니 개발 생산성이 올라갑니다.  
새로운 업무나 데이터 흐름이 생겨도 기존 흐름에 영향 없이 확장이 가능합니다.  
시스템 간 결합도를 낮추고, 응집도를 높힐 수 있습니다.  
장애가 전파되지 않습니다.

### 2️⃣ 압도적인 성능!!!
하나의 `topic`을 여러 개의 `partition`으로 나누어 데이터를 여러 서버에 분산 저장할 수 있습니다.   
수만, 수십만 건의 메시지도 동시에 처리 가능합니다.  
`consumer`가 병렬로 데이터를 꺼낼 수 있다 → ***병목 없는 고성능***

> 💡 `kafka`는 `topic`으로 데이터 흐름을 깔끔하게 분리하고, `partition`으로 성능과 안정성을 보장합니다.  
> 이 두 가지 구조 덕분에 `실시간`, `대용량`, `고신뢰 데이터 플랫폼`의 대표주자가 되었습니다!

## 🤖 kafka + spring + kotlin 예시
### 📨 Producer = 메시지 보내는 사람 
```kotlin
class LetterProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
){
    fun send(){
        kafkaTemplate.send(topic, message)
    }
}
```
### 📩 Consumer = 메시지 받는 사람
```kotlin
class LetterConsumer(
    private val consumerFactory: ConsumerFactory<String, ByteArray>
){
    fun consume(){
        consumerFactory.createConsumer().use { consumer ->
            consumer.subscribe(listOf(topic))
            val records = consumer.poll(Duration.ofSeconds(5))
        }
    }
}
```