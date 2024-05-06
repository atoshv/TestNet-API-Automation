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
    And param amount = global.amount
    And param partnerId = global.partnerId
    And param slippageTolerance = global.slippageTolerance
    And param destFuel = global.destFuel
    When method GET
#    Then match responseStatus == 200
