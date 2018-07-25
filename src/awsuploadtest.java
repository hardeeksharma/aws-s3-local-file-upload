import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class awsuploadtest {
	static AmazonS3 s3client;
	String endUrl = "http://cmwkyc.s3-website.us-east-2.amazonaws.com";
	String bucketPath = "https://s3.us-east-2.amazonaws.com/cmwkyc/UsersImages";

	public static void main(String[] args) throws FileNotFoundException {

		try {

			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ7YPSWUFTMQCE4LA",
					"OedBTy/sUlRqSSvm1+7Jfk+KVFbtjwrCWZEi1Pnr");

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2")
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

			File file = new File("C:\\Users\\hardeeksharma\\Desktop\\img.jpg");
			
			FileInputStream stream = new FileInputStream("C:\\Users\\hardeeksharma\\Desktop\\img.jpg");
			ObjectMetadata objectMetadata = new ObjectMetadata();

			objectMetadata.setContentType("image/jpeg");
			PutObjectRequest putObjectRequest = new PutObjectRequest("cmwkyc", "img/" + file.getName(), stream,
					objectMetadata);
			putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
			PutObjectResult result = s3Client.putObject(putObjectRequest);
			ObjectMetadata mete = result.getMetadata();

			System.out.println(mete.getUserMetadata());

			System.out.println("Etag:" + result.getETag() + "-->" + result);
			System.out.println("uploded");
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}

}
