Feature: Dynamic API Invocation for WETH

  Background:
    * def global = Java.type('routerProtocol.common.Global')

  @ignore
  Scenario:WETH
    Given url 'https://api-beta.pathfinder.routerprotocol.com/api/v2/quote'
    And param fromTokenAddress = parentAddress
    And param fromTokenChainId = parentChainId
    And param toTokenAddress = conversionAddress
    And param toTokenChainId = conversionChainId
    * print ParentWETHDecimal
    * def valAmount = (0.1 * Math.pow(10, ParentWETHDecimal)).toFixed(0)
    * print valAmount
    And param amount = valAmount
    And param partnerId = global.partnerId
    And param slippageTolerance = global.slippageTolerance
    And param destFuel = global.destFuel
    When method GET
    Then match responseStatus == 200
