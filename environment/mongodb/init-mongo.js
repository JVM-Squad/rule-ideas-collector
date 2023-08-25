db = db.getSiblingDB('rule_ideas_collector_test')
db.createUser(
    {
        user: "admin",
        pwd: "password",
        roles: [
            {
                role: "readWrite",
                db: "rule_ideas_collector_test"
            }
        ]
    }
);

// DB is created only when it is populated, so we add a test collection (for now)
db.createCollection('test')

