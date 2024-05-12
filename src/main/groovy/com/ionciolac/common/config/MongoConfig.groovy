package com.ionciolac.common.config


import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories("com.ionciolac.adapter.outputs.persistence.repository")
@EnableMongoAuditing
class MongoConfig {
}
