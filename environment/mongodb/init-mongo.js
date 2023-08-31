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

db.createCollection('user')
db.user.insert(
    [
        { "_id": "id_0", "name": "Tyler" },
        { "_id": "id_1", "name": "Angelo" },
        { "_id": "id_2", "name": "Johann" }
    ]
)

db.createCollection('rule')
db.rule.insert(
    [
        {
             "_id": "Rule_1",
             "title": "Rule 1",
             "languages": ["English"],
             "tags": ["Fightclub"],
             "isSonarWay": "true",
             "status": "PROPOSAL",
             "creator": { "_id": "id_0", "name": "Tyler" },
             "description": "You do not talk about the Fight Club",
             "comments": [ ],
              "_class": 'org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule'
        },
        {
             "_id": "Rule_2",
             "title": "Rule 2",
             "languages": ["English"],
             "tags": ["Fightclub"],
             "isSonarWay": "true",
             "status": "PROPOSAL",
             "creator": { "_id": "id_0", "name": "Tyler" },
             "description": "You are the Fightclub and you do not talk about the Fight Club",
             "comments": [ ],
              "_class": 'org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule'
        }
    ]
)