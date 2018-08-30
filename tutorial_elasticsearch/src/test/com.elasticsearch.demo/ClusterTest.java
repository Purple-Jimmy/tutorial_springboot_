package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  21:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class ClusterTest {
    @Autowired
    private JestClient jestClient;

    /**
     * 查看节点信息
     * @throws IOException
     */
    @Test
    public void nodeInfo() throws IOException {
        NodesInfo nodesInfo = new NodesInfo.Builder().build();
        JestResult jestResult = jestClient.execute(nodesInfo);
        System.out.println(jestResult.getJsonString());
        //{"_nodes":{"total":1,"successful":1,"failed":0},"cluster_name":"jimmy-application","nodes":{"TTcu93ieRCeQ5WF4U_9Q-A":{"name":"node-1","transport_address":"192.168.0.23:9300","host":"192.168.0.23","ip":"192.168.0.23","version":"6.1.1","build_hash":"bd92e7f","total_indexing_buffer":103795916,"roles":["master","data","ingest"],"settings":{"pidfile":"/var/run/elasticsearch/elasticsearch.pid","cluster":{"name":"jimmy-application"},"node":{"name":"node-1"},"path":{"data":["/var/lib/elasticsearch"],"logs":"/var/log/elasticsearch","home":"/usr/share/elasticsearch"},"discovery":{"zen":{"ping":{"unicast":{"hosts":["0.0.0.0"]}}}},"client":{"type":"node"},"http":{"type":{"default":"netty4"}},"transport":{"type":{"default":"netty4"}},"network":{"host":"0.0.0.0"}},"os":{"refresh_interval_in_millis":1000,"name":"Linux","arch":"amd64","version":"3.10.0-327.el7.x86_64","available_processors":8,"allocated_processors":8},"process":{"refresh_interval_in_millis":1000,"id":21070,"mlockall":false},"jvm":{"pid":21070,"version":"1.8.0_151","vm_name":"Java HotSpot(TM) 64-Bit Server VM","vm_version":"25.151-b12","vm_vendor":"Oracle Corporation","start_time_in_millis":1516342028424,"mem":{"heap_init_in_bytes":1073741824,"heap_max_in_bytes":1037959168,"non_heap_init_in_bytes":2555904,"non_heap_max_in_bytes":0,"direct_max_in_bytes":1037959168},"gc_collectors":["ParNew","ConcurrentMarkSweep"],"memory_pools":["Code Cache","Metaspace","Compressed Class Space","Par Eden Space","Par Survivor Space","CMS Old Gen"],"using_compressed_ordinary_object_pointers":"true","input_arguments":["-Xms1g","-Xmx1g","-XX:+UseConcMarkSweepGC","-XX:CMSInitiatingOccupancyFraction=75","-XX:+UseCMSInitiatingOccupancyOnly","-XX:+AlwaysPreTouch","-Xss1m","-Djava.awt.headless=true","-Dfile.encoding=UTF-8","-Djna.nosys=true","-XX:-OmitStackTraceInFastThrow","-Dio.netty.noUnsafe=true","-Dio.netty.noKeySetOptimization=true","-Dio.netty.recycler.maxCapacityPerThread=0","-Dlog4j.shutdownHookEnabled=false","-Dlog4j2.disable.jmx=true","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/var/lib/elasticsearch","-Des.path.home=/usr/share/elasticsearch","-Des.path.conf=/etc/elasticsearch"]},"thread_pool":{"force_merge":{"type":"fixed","min":1,"max":1,"queue_size":-1},"fetch_shard_started":{"type":"scaling","min":1,"max":16,"keep_alive":"5m","queue_size":-1},"listener":{"type":"fixed","min":4,"max":4,"queue_size":-1},"index":{"type":"fixed","min":8,"max":8,"queue_size":200},"refresh":{"type":"scaling","min":1,"max":4,"keep_alive":"5m","queue_size":-1},"generic":{"type":"scaling","min":4,"max":128,"keep_alive":"30s","queue_size":-1},"warmer":{"type":"scaling","min":1,"max":4,"keep_alive":"5m","queue_size":-1},"search":{"type":"fixed_auto_queue_size","min":13,"max":13,"queue_size":1000},"flush":{"type":"scaling","min":1,"max":4,"keep_alive":"5m","queue_size":-1},"fetch_shard_store":{"type":"scaling","min":1,"max":16,"keep_alive":"5m","queue_size":-1},"management":{"type":"scaling","min":1,"max":5,"keep_alive":"5m","queue_size":-1},"get":{"type":"fixed","min":8,"max":8,"queue_size":1000},"bulk":{"type":"fixed","min":8,"max":8,"queue_size":200},"snapshot":{"type":"scaling","min":1,"max":4,"keep_alive":"5m","queue_size":-1}},"transport":{"bound_address":["0.0.0.0:9300"],"publish_address":"192.168.0.23:9300","profiles":{}},"http":{"bound_address":["0.0.0.0:9200"],"publish_address":"192.168.0.23:9200","max_content_length_in_bytes":104857600},"plugins":[{"name":"analysis-ik","version":"6.1.1","description":"IK Analyzer for Elasticsearch","classname":"org.elasticsearch.plugin.analysis.ik.AnalysisIkPlugin","has_native_controller":false,"requires_keystore":false},{"name":"analysis-pinyin","version":"6.1.1","description":"Pinyin Analysis for Elasticsearch","classname":"org.elasticsearch.plugin.analysis.pinyin.AnalysisPinyinPlugin","has_native_controller":false,"requires_keystore":false}],"modules":[{"name":"aggs-matrix-stats","version":"6.1.1","description":"Adds aggregations whose input are a list of numeric fields and output includes a matrix.","classname":"org.elasticsearch.search.aggregations.matrix.MatrixAggregationPlugin","has_native_controller":false,"requires_keystore":false},{"name":"analysis-common","version":"6.1.1","description":"Adds \"built in\" analyzers to Elasticsearch.","classname":"org.elasticsearch.analysis.common.CommonAnalysisPlugin","has_native_controller":false,"requires_keystore":false},{"name":"ingest-common","version":"6.1.1","description":"Module for ingest processors that do not require additional security permissions or have large dependencies and resources","classname":"org.elasticsearch.ingest.common.IngestCommonPlugin","has_native_controller":false,"requires_keystore":false},{"name":"lang-expression","version":"6.1.1","description":"Lucene expressions integration for Elasticsearch","classname":"org.elasticsearch.script.expression.ExpressionPlugin","has_native_controller":false,"requires_keystore":false},{"name":"lang-mustache","version":"6.1.1","description":"Mustache scripting integration for Elasticsearch","classname":"org.elasticsearch.script.mustache.MustachePlugin","has_native_controller":false,"requires_keystore":false},{"name":"lang-painless","version":"6.1.1","description":"An easy, safe and fast scripting language for Elasticsearch","classname":"org.elasticsearch.painless.PainlessPlugin","has_native_controller":false,"requires_keystore":false},{"name":"mapper-extras","version":"6.1.1","description":"Adds advanced field mappers","classname":"org.elasticsearch.index.mapper.MapperExtrasPlugin","has_native_controller":false,"requires_keystore":false},{"name":"parent-join","version":"6.1.1","description":"This module adds the support parent-child queries and aggregations","classname":"org.elasticsearch.join.ParentJoinPlugin","has_native_controller":false,"requires_keystore":false},{"name":"percolator","version":"6.1.1","description":"Percolator module adds capability to index queries and query these queries by specifying documents","classname":"org.elasticsearch.percolator.PercolatorPlugin","has_native_controller":false,"requires_keystore":false},{"name":"reindex","version":"6.1.1","description":"The Reindex module adds APIs to reindex from one index to another or update documents in place.","classname":"org.elasticsearch.index.reindex.ReindexPlugin","has_native_controller":false,"requires_keystore":false},{"name":"repository-url","version":"6.1.1","description":"Module for URL repository","classname":"org.elasticsearch.plugin.repository.url.URLRepositoryPlugin","has_native_controller":false,"requires_keystore":false},{"name":"transport-netty4","version":"6.1.1","description":"Netty 4 based transport implementation","classname":"org.elasticsearch.transport.Netty4Plugin","has_native_controller":false,"requires_keystore":false},{"name":"tribe","version":"6.1.1","description":"Tribe module","classname":"org.elasticsearch.tribe.TribePlugin","has_native_controller":false,"requires_keystore":false}],"ingest":{"processors":[{"type":"append"},{"type":"convert"},{"type":"date"},{"type":"date_index_name"},{"type":"dot_expander"},{"type":"fail"},{"type":"foreach"},{"type":"grok"},{"type":"gsub"},{"type":"join"},{"type":"json"},{"type":"kv"},{"type":"lowercase"},{"type":"remove"},{"type":"rename"},{"type":"script"},{"type":"set"},{"type":"sort"},{"type":"split"},{"type":"trim"},{"type":"uppercase"},{"type":"urldecode"}]}}}}
    }

    /**
     * 查看节点status
     * @throws IOException
     */
    @Test
    public void nodesStats() throws IOException {
        NodesStats nodesStats = new NodesStats.Builder().build();
        JestResult jestResult = jestClient.execute(nodesStats);
        System.out.println(jestResult.getJsonString());
        //{"_nodes":{"total":1,"successful":1,"failed":0},"cluster_name":"jimmy-application","nodes":{"TTcu93ieRCeQ5WF4U_9Q-A":{"timestamp":1517234141633,"name":"node-1","transport_address":"192.168.0.23:9300","host":"192.168.0.23","ip":"192.168.0.23:9300","roles":["master","data","ingest"],"indices":{"docs":{"count":118,"deleted":1},"store":{"size_in_bytes":212554},"indexing":{"index_total":141,"index_time_in_millis":320,"index_current":0,"index_failed":0,"delete_total":10,"delete_time_in_millis":7,"delete_current":0,"noop_update_total":0,"is_throttled":false,"throttle_time_in_millis":0},"get":{"total":25,"time_in_millis":26,"exists_total":23,"exists_time_in_millis":26,"missing_total":2,"missing_time_in_millis":0,"current":0},"search":{"open_contexts":0,"query_total":943,"query_time_in_millis":370,"query_current":0,"fetch_total":164,"fetch_time_in_millis":76,"fetch_current":0,"scroll_total":10,"scroll_time_in_millis":195,"scroll_current":0,"suggest_total":0,"suggest_time_in_millis":0,"suggest_current":0},"merges":{"current":0,"current_docs":0,"current_size_in_bytes":0,"total":0,"total_time_in_millis":0,"total_docs":0,"total_size_in_bytes":0,"total_stopped_time_in_millis":0,"total_throttled_time_in_millis":0,"total_auto_throttle_in_bytes":1866465280},"refresh":{"total":490,"total_time_in_millis":738,"listeners":0},"flush":{"total":64,"total_time_in_millis":380},"warmer":{"current":0,"total":184,"total_time_in_millis":6},"query_cache":{"memory_size_in_bytes":0,"total_count":0,"hit_count":0,"miss_count":0,"cache_size":0,"cache_count":0,"evictions":0},"fielddata":{"memory_size_in_bytes":0,"evictions":0},"completion":{"size_in_bytes":0},"segments":{"count":25,"memory_in_bytes":96419,"terms_memory_in_bytes":75405,"stored_fields_memory_in_bytes":7800,"term_vectors_memory_in_bytes":0,"norms_memory_in_bytes":9792,"points_memory_in_bytes":90,"doc_values_memory_in_bytes":3332,"index_writer_memory_in_bytes":0,"version_map_memory_in_bytes":0,"fixed_bit_set_memory_in_bytes":0,"max_unsafe_auto_id_timestamp":-1,"file_sizes":{}},"translog":{"operations":113,"size_in_bytes":27635,"uncommitted_operations":0,"uncommitted_size_in_bytes":2408},"request_cache":{"memory_size_in_bytes":0,"evictions":0,"hit_count":0,"miss_count":46},"recovery":{"current_as_source":0,"current_as_target":0,"throttle_time_in_millis":0}},"os":{"timestamp":1517234141646,"cpu":{"percent":0,"load_average":{"1m":0.03,"5m":0.02,"15m":0.05}},"mem":{"total_in_bytes":8187617280,"free_in_bytes":4208955392,"used_in_bytes":3978661888,"free_percent":51,"used_percent":49},"swap":{"total_in_bytes":2147479552,"free_in_bytes":2147479552,"used_in_bytes":0},"cgroup":{"cpuacct":{"control_group":"/","usage_nanos":17386013262154},"cpu":{"control_group":"/","cfs_period_micros":100000,"cfs_quota_micros":-1,"stat":{"number_of_elapsed_periods":0,"number_of_times_throttled":0,"time_throttled_nanos":0}},"memory":{"control_group":"/","limit_in_bytes":"9223372036854775807","usage_in_bytes":"3696394240"}}},"process":{"timestamp":1517234141646,"open_file_descriptors":381,"max_file_descriptors":65536,"cpu":{"percent":0,"total_in_millis":2949600},"mem":{"total_virtual_in_bytes":7204020224}},"jvm":{"timestamp":1517234141647,"uptime_in_millis":892113475,"mem":{"heap_used_in_bytes":657131440,"heap_used_percent":63,"heap_committed_in_bytes":1037959168,"heap_max_in_bytes":1037959168,"non_heap_used_in_bytes":101544936,"non_heap_committed_in_bytes":108826624,"pools":{"young":{"used_in_bytes":269177424,"max_in_bytes":286326784,"peak_used_in_bytes":286326784,"peak_max_in_bytes":286326784},"survivor":{"used_in_bytes":1174336,"max_in_bytes":35782656,"peak_used_in_bytes":35782656,"peak_max_in_bytes":35782656},"old":{"used_in_bytes":386779680,"max_in_bytes":715849728,"peak_used_in_bytes":386779680,"peak_max_in_bytes":715849728}}},"threads":{"count":81,"peak_count":84},"gc":{"collectors":{"young":{"collection_count":398,"collection_time_in_millis":2855},"old":{"collection_count":1,"collection_time_in_millis":44}}},"buffer_pools":{"direct":{"count":80,"used_in_bytes":169068596,"total_capacity_in_bytes":169068595},"mapped":{"count":25,"used_in_bytes":176954,"total_capacity_in_bytes":176954}},"classes":{"current_loaded_count":12162,"total_loaded_count":12162,"total_unloaded_count":0}},"thread_pool":{"bulk":{"threads":8,"queue":0,"active":0,"rejected":0,"largest":8,"completed":63},"fetch_shard_started":{"threads":1,"queue":0,"active":0,"rejected":0,"largest":16,"completed":26},"fetch_shard_store":{"threads":0,"queue":0,"active":0,"rejected":0,"largest":0,"completed":0},"flush":{"threads":1,"queue":0,"active":0,"rejected":0,"largest":4,"completed":128},"force_merge":{"threads":0,"queue":0,"active":0,"rejected":0,"largest":0,"completed":0},"generic":{"threads":5,"queue":0,"active":0,"rejected":0,"largest":5,"completed":335201},"get":{"threads":8,"queue":0,"active":0,"rejected":0,"largest":8,"completed":20},"index":{"threads":8,"queue":0,"active":0,"rejected":0,"largest":8,"completed":8},"listener":{"threads":0,"queue":0,"active":0,"rejected":0,"largest":0,"completed":0},"management":{"threads":4,"queue":0,"active":1,"rejected":0,"largest":4,"completed":830326},"refresh":{"threads":4,"queue":0,"active":0,"rejected":0,"largest":4,"completed":7024399},"search":{"threads":13,"queue":0,"active":0,"rejected":0,"largest":13,"completed":1239},"snapshot":{"threads":0,"queue":0,"active":0,"rejected":0,"largest":0,"completed":0},"warmer":{"threads":0,"queue":0,"active":0,"rejected":0,"largest":0,"completed":0}},"fs":{"timestamp":1517234141648,"total":{"total_in_bytes":40279195648,"free_in_bytes":35058495488,"available_in_bytes":35058495488},"least_usage_estimate":{"path":"/var/lib/elasticsearch/nodes/0","total_in_bytes":40279195648,"available_in_bytes":35058495488,"used_disk_percent":12.961282061398933},"most_usage_estimate":{"path":"/var/lib/elasticsearch/nodes/0","total_in_bytes":40279195648,"available_in_bytes":35058495488,"used_disk_percent":12.961282061398933},"data":[{"path":"/var/lib/elasticsearch/nodes/0","mount":"/ (rootfs)","type":"rootfs","total_in_bytes":40279195648,"free_in_bytes":35058495488,"available_in_bytes":35058495488}],"io_stats":{"devices":[{"device_name":"dm-0","operations":332598,"read_operations":15,"write_operations":332583,"read_kilobytes":144,"write_kilobytes":1802595}],"total":{"operations":332598,"read_operations":15,"write_operations":332583,"read_kilobytes":144,"write_kilobytes":1802595}}},"transport":{"server_open":0,"rx_count":0,"rx_size_in_bytes":0,"tx_count":1,"tx_size_in_bytes":23},"http":{"current_open":3,"total_opened":2536},"breakers":{"request":{"limit_size_in_bytes":622775500,"limit_size":"593.9mb","estimated_size_in_bytes":0,"estimated_size":"0b","overhead":1.0,"tripped":0},"fielddata":{"limit_size_in_bytes":622775500,"limit_size":"593.9mb","estimated_size_in_bytes":0,"estimated_size":"0b","overhead":1.03,"tripped":0},"in_flight_requests":{"limit_size_in_bytes":1037959168,"limit_size":"989.8mb","estimated_size_in_bytes":0,"estimated_size":"0b","overhead":1.0,"tripped":0},"parent":{"limit_size_in_bytes":726571417,"limit_size":"692.9mb","estimated_size_in_bytes":0,"estimated_size":"0b","overhead":1.0,"tripped":0}},"script":{"compilations":0,"cache_evictions":0},"discovery":{"cluster_state_queue":{"total":0,"pending":0,"committed":0},"published_cluster_states":{"full_states":0,"incompatible_diffs":0,"compatible_diffs":0}},"ingest":{"total":{"count":0,"time_in_millis":0,"current":0,"failed":0},"pipelines":{}},"adaptive_selection":{"TTcu93ieRCeQ5WF4U_9Q-A":{"outgoing_searches":0,"avg_queue_size":0,"avg_service_time_ns":418409,"avg_response_time_ns":403965,"rank":"0.4"}}}}}
    }

    /**
     * 查看health情况
     * @throws IOException
     */
    @Test
    public void healthInfo() throws IOException {
        Health health = new Health.Builder().build();
        JestResult jestResult = jestClient.execute(health);
        System.out.println(jestResult.getJsonString());
    }
}