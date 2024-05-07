Feature: Dynamic API Invocation for USDT

    Background:
        * def global = Java.type('routerProtocol.common.Global')

    @ignore
    Scenario:USDT
        Given url 'https://api-beta.pathfinder.routerprotocol.com/api/v2/quote'
        And param fromTokenAddress = parentAddress
        And param fromTokenChainId = parentChainId
        And param toTokenAddress = conversionAddress
        And param toTokenChainId = conversionChainId
        * print ParentUSDTDecimal
        * def valAmount = (100 * Math.pow(10, ParentUSDTDecimal)).toFixed(0)
        * print valAmount
        And param amount = valAmount
        And param partnerId = global.partnerId
        And param slippageTolerance = global.slippageTolerance
        And param destFuel = global.destFuel
        When method GET
        Then match responseStatus == 200