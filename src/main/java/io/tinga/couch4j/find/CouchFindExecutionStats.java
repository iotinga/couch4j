package io.tinga.couch4j.find;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Statistics of find operation execution. Useful
 * for performance insights.
 */
public class CouchFindExecutionStats {
    private Integer totalKeysExamined;
    private Integer totalDocsExamined;
    private Integer totalQuorumDocsExamined;
    private Integer resultsReturned;
    private Float executionTimeMs;

    /**
     * Get the number of keys examined
     * 
     * @return
     */
    @JsonGetter("total_keys_examined")
    public Integer getTotalKeysExamined() {
        return totalKeysExamined;
    }

    /**
     * Get the number of document examined. Ideally this number
     * shall be small.
     * 
     * @return
     */
    @JsonGetter("total_docs_examined")
    public Integer getTotalDocsExamined() {
        return totalDocsExamined;
    }

    /**
     * Get the number of quorum document examined
     * 
     * @return
     */
    @JsonGetter("total_quorum_docs_examined")
    public Integer getTotalQuorumDocsExamined() {
        return totalQuorumDocsExamined;
    }

    /**
     * Get the number of results returned in this response
     * 
     * @return
     */
    @JsonGetter("results_returned")
    public Integer getResultsReturned() {
        return resultsReturned;
    }

    /**
     * Get how much time the query took in milliseconds
     * 
     * @return
     */
    @JsonGetter("execution_time_ms")
    public Float getExecutionTimeMs() {
        return executionTimeMs;
    }
}
