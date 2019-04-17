package com.omid.cloud.client;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloud.omid.common.TrxVO;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.execution.DataFetcherResult;
import graphql.language.SourceLocation;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class TrxResolver
{

    private static final Logger log = LoggerFactory.getLogger(GraphQLController.class);

    TrxProxy trxProxy;

    @Autowired
    public TrxResolver(TrxProxy trxProxy)
    {
        this.trxProxy = trxProxy;
    }

    @GraphQLQuery(name = "findTrxById")
    public DataFetcherResult<TrxVO> findTrxById(@GraphQLArgument(name = "trxId") String trxId)
    {
        log.info("Call TRX PROXY");
        TrxVO test = trxProxy.test();
        return new DataFetcherResult<TrxVO>(test, Arrays.asList(new GraphErr()));
    }
    
    @GraphQLQuery(name = "findTrxByIdEx")
    public DataFetcherResult<TrxVO> findTrxByIdEx(@GraphQLArgument(name = "trxId") String trxId)
    {
        log.info("Call TRX PROXY");
        TrxVO test = trxProxy.test();
        return new DataFetcherResult<TrxVO>(test, Arrays.asList(new GraphErr("something bad happened")));
    }

    public static class GraphErr implements GraphQLError
    {

        private String message;

        public GraphErr(String message)
        {
            this.message = message;
        }

        public GraphErr()
        {
        }

        @Override
        public ErrorType getErrorType()
        {
            return ErrorType.ValidationError;
        }

        @Override
        public List<SourceLocation> getLocations()
        {
            return null;
        }

        @Override
        public String getMessage()
        {
            return message;
        }

    }

}
