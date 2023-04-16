package contracts.messaging
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    // The Identifier which can be used to identify it later.
    label 'ProductChanged'
    input {
        // Contract will be triggered by the following method.
        triggeredBy('productChanged()')
    }
    outputMessage {
        sentTo 'eventTopic'
        // Payload specification that a JSON message must have
        body(
                eventType: "ProductChanged",
                productId: 1,
                productName: "TV",
                productPrice: 10000,
                productStock: 10,
                imageUrl: "tv.jpg"
        )
        bodyMatchers {
            jsonPath('$.eventType', byRegex("ProductChanged"))
            jsonPath('$.productId', byRegex(nonEmpty()).asLong())
            jsonPath('$.productName', byRegex(nonEmpty()).asString())
            jsonPath('$.productPrice', byRegex(nonEmpty()).asLong())
            jsonPath('$.productStock', byRegex(nonEmpty()).asLong())
            jsonPath('$.imageUrl', byRegex(nonEmpty()).asString())
        }
        headers {
            messagingContentType(applicationJson())
        }
    }
}
