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

db.createCollection("test"); //MongoDB creates the database when you first store data in that database