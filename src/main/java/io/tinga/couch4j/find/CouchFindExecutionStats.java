package io.tinga.couch4j.find;

/**
 * Statistics of find operation execution. Useful
 * for performance insights.
 */
public class CouchFindExecutionStats {
    private Integer total_keys_examined;
    private Integer total_docs_examined;
    private Integer total_quorum_docs_examined;
    private Integer results_returned;
    private Float execution_time_ms;

    public Integer getTotalKeysExamined() {
        return total_keys_examined;
    }

    public Integer getTotalDocsExamined() {
        return total_docs_examined;
    }

    public Integer getTotalQuorumDocsExamined() {
        return total_quorum_docs_examined;
    }

    public Integer getResultsReturned() {
        return results_returned;
    }

    public Float getExecutionTimeMs() {
        return execution_time_ms;
    }
}
