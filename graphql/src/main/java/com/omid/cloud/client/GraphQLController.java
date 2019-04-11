package com.omid.cloud.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.AsyncSerialExecutionStrategy;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@RestController
public class GraphQLController
{

    private static final Logger log = LoggerFactory.getLogger(GraphQLController.class);

    private GraphQL graphQL;
    
    @Autowired
    public GraphQLController(TrxResolver trxResolver)
    {
        GraphQLSchemaGenerator gen = new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder());
        gen.withOperationsFromSingleton(trxResolver);
        GraphQLSchema schema = gen.withValueMapperFactory(new JacksonValueMapperFactory()).generate();
        graphQL = GraphQL.newGraphQL(schema).queryExecutionStrategy(new AsyncExecutionStrategy())
                .mutationExecutionStrategy(new AsyncSerialExecutionStrategy()).build();
    }
    
    
    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> graphql(@RequestBody Map<String, Object> reqBody, HttpServletRequest req)
    {

        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput().query(reqBody.get("query").toString())
                .operationName(reqBody.get("operationName") != null ? reqBody.get("operationName").toString() : null).context(req)
                .variables((Map<String, Object>) reqBody.get("variables")).build());

        return executionResult.toSpecification();
    }

}
