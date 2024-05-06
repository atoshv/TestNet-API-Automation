function prepareUSDCTransactions() {
    var jsonData = read('classpath:routerProtocol/testData/input.json')
    karate.forEach(jsonData, function(iteration, index) {
        var conversions = iteration.conversion;
        karate.forEach(conversions, function(conv, index) {
                      karate.call('classpath:routerProtocol/testScripts/testUSDC.feature', {
                      parentAddress: iteration.USDCAddress,
                      parentChainId: iteration.chainId,
                      conversionAddress: conv.USDCAddress,
                      conversionChainId: conv.chainId
                    });
                });
            });
        }

