def globals = [:]
// define the default TraversalSource to bind queries to - this one will be named "g".

globals << [hook: [
        onStartUp : { ctx ->
            ctx.logger.warn("Creating indexes.")
            graph.tx().rollback()
            res = graph.openManagement().containsGraphIndex('insightIdMixedIndex')
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

                if(mgmt.getVertexLabel("Biographics") == null) {
                    mgmt.makeVertexLabel("Biographics").make()
                }
                if(mgmt.getVertexLabel("Event") == null) {
                    mgmt.makeVertexLabel("Event").make()
                }
                if(mgmt.getVertexLabel("Organisation") == null) {
                    mgmt.makeVertexLabel("Organisation").make()
                }
                if(mgmt.getVertexLabel("RawData") == null) {
                    mgmt.makeVertexLabel("RawData").make()
                }
                if(mgmt.getVertexLabel("Location") == null) {
                    mgmt.makeVertexLabel("Location").make()
                }
                if(mgmt.getVertexLabel("Equipment") == null) {
                    mgmt.makeVertexLabel("Equipment").make()
                }

                mgmt.commit()
            }
        },
        onShutDown: { ctx ->
            ctx.logger.warn("Executed once at shutdown of Gremlin Server.")
        }
] as LifeCycleHook]
globals << [g   : graph.traversal(),
            mgmt: graph.openManagement()]
