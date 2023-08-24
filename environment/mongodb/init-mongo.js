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

conn = new Mongo();
db = conn.getDB("rule_ideas_collector_test");

// TODO populate the DB!
