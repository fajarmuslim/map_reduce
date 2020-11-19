import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Average {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, FloatWritable>{

        private Text outputKey = new Text(); ;
        private FloatWritable outputValue = new FloatWritable();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            
            String[] arr = value.toString().split(",");
            String id = arr[0];
            float jumlahTransaksi = Float.parseFloat(arr[1]);
            outputKey.set(id);           
            outputValue.set(jumlahTransaksi); 
            context.write(outputKey, outputValue);
        }
    }

    public static class AverageReducer
            extends Reducer<Text,FloatWritable,Text,FloatWritable> {
        
        private FloatWritable outputValue = new FloatWritable();
        
        public void reduce(Text key, Iterable<FloatWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            float sum = 0; 
            int count = 0; 
            float avg;

            for (FloatWritable jumlahTrans:values){
                sum = sum + jumlahTrans.get(); 
                count++;
            }

            avg = sum/count ;
            outputValue.set(avg);
            context.write(key, outputValue);
        }
    }

    public static void main(String[] args) throws Exception {
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Average");
        job.setJarByClass(Average.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(AverageReducer.class);
        job.setReducerClass(AverageReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}