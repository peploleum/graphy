def globals = [:]
// define the default TraversalSource to bind queries to - this one will be named "g".

globals << [hook: [
        onStartUp : { ctx ->
            ctx.logger.warn("Creating indexes.")
            graph.tx().rollback()
            res = graph.openManagement().containsGraphIndex('insightIdMixedIndex')
            //res2 = graph.openManagement().containsGraphIndex('insightIdCompositeIndex')
            ctx.logger.warn("Indexes already exists: " + res)
            if (res) {
                ctx.logger.warn("Mixed index already exists.")
            } else {
                mgmt = graph.openManagement()
                propertyKey = mgmt.getPropertyKey('idInsight')
                if (propertyKey == null) {
                    propertyKey = mgmt.makePropertyKey('idInsight').dataType(String.class).make()
                }
                ctx.logger.warn("property key  : " + propertyKey.toString())
                mixedIndex = mgmt.buildIndex('insightIdMixedIndex', Vertex.class).addKey(propertyKey).buildMixedIndex("search")
                ctx.logger.warn("mixedIndex  : " + mixedIndex.toString())

                res = graph.openManagement().containsGraphIndex('insightIdMixedIndex')
                ctx.logger.warn("Index created : " + res.toString())
				
				mgmt.makeVertexLabel("Biographics").make()
				mgmt.makeVertexLabel("Event").make()
				mgmt.makeVertexLabel("Organisation").make()
				mgmt.makeVertexLabel("RawData").make()
				mgmt.makeVertexLabel("Location").make()
				mgmt.makeVertexLabel("Equipment").make()

				mgmt.commit()
            }
//            if (res2) {
//                ctx.logger.warn("Composite index already exists.")
//            } else {
//                mgmt = graph.openManagement()
//                propertyKey2 = mgmt.getPropertyKey('idInsight')
//                if (propertyKey2 == null) {
//                    propertyKey2 = mgmt.makePropertyKey('idInsight').dataType(String.class).make()
//                }
//                ctx.logger.warn("property key  : " + propertyKey2.toString())
//                compositeIndex = mgmt.buildIndex('insightIdCompositeIndex', Vertex.class).addKey(propertyKey2).buildCompositeIndex()
//                ctx.logger.warn("compositeIndex  : " + compositeIndex.toString())
//
//                res = graph.openManagement().containsGraphIndex('insightIdCompositeIndex')
//                ctx.logger.warn("Index created : " + res2.toString())
//            }
            
        },
        onShutDown: { ctx ->
            ctx.logger.warn("Executed once at shutdown of Gremlin Server.")
        }
] as LifeCycleHook]
globals << [g   : graph.traversal(),
            mgmt: graph.openManagement()]

