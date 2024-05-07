function prepareUSDTTransactions() {
    var jsonData = read('classpath:routerProtocol/testData/input.json')
    karate.forEach(jsonData, function(iteration, index) {
        var conversions = iteration.conversion;
        karate.forEach(conversions, function(conv, index) {
                      karate.call('classpath:routerProtocol/testScripts/testUSDT.feature', {
                      parentAddress: iteration.USDTAddress,
                      parentChainId: iteration.chainId,
                      conversionAddress: conv.USDTAddress,
                      conversionChainId: conv.chainId,
                      ParentUSDTDecimal: iteration.USDTDecimal
                    });
                });
            });
        }

