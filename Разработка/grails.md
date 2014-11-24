
    
    AggregationOutput qty = Invoice.collection.aggregate(
                [ $group :
                      [ _id : [ code: '$code' ] ]
                ]
            );
    
            for (DBObject obj : qty.results()) {
                String id = obj.get("_id");
                log.info id
            }
