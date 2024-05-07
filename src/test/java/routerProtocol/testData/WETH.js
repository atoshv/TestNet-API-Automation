function prepareWETHTransactions() {
    var jsonData = read('classpath:routerProtocol/testData/input.json')
    karate.forEach(jsonData, function(iteration, index) {
        var conversions = iteration.conversion;
        karate.forEach(conversions, function(conv, index) {
                      karate.call('classpath:routerProtocol/testScripts/testWETH.feature', {
                      parentAddress: iteration.WETHAddress,
                      parentChainId: iteration.chainId,
                      conversionAddress: conv.WETHAddress,
                      conversionChainId: conv.chainId,
                      ParentWETHDecimal: iteration.WETHDecimal
                    });
                });
            });
        }

