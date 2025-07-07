package FileHandler;

//Imports
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoTranscoderInteractiveTest
{
    
    public static void main(String[] args)
    {
        VideoTranscoderInteractiveTest test = new VideoTranscoderInteractiveTest();
        
        System.out.println("=== VideoTranscoder Test ===");
        
        // Test single file conversion
        test.testSingleFileConversion();
        
        // Test folder processing
        test.testFolderProcessing();
        
        System.out.println("=== Test Complete ===");
    }
    
    public void testSingleFileConversion()
    {
        System.out.println("\n--- Testing Single File Conversion ---");
        
        // Update these paths to point to your actual test files
        Path inputFile = Paths.get("assets/TestThingy.MOV");
        Path outputFile = Paths.get("assets/TestThingy.MP4");
        
        System.out.println("Input file: " + inputFile);
        System.out.println("Output file: " + outputFile);
        
        try {
            long startTime = System.currentTimeMillis();
            
            VideoTranscoder.transcodeToMp4(inputFile, outputFile);
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("✓ Single file conversion completed successfully!");
            System.out.println("  Duration: " + duration + " ms (" + duration/1000.0 + " seconds)");
            
        } catch (Exception e) {
            System.err.println("✗ Single file conversion failed:");
            e.printStackTrace();
        }
    }
    
    public void testFolderProcessing()
    {
        System.out.println("\n--- Testing Folder Processing ---");
        
        // Update this path to point to your test folder containing .MOV files
        Path testFolder = Paths.get("assets");
        
        System.out.println("Processing folder: " + testFolder);
        
        VideoTranscoder transcoder = new VideoTranscoder();
        
        try
        {
            long startTime = System.currentTimeMillis();
            
            transcoder.processFolder(testFolder);
            
            // Wait a bit for async processing to complete
            System.out.println("Processing started... waiting for completion...");
            Thread.sleep(2000); // Give it 2 seconds to start processing
            
            // Shutdown and wait for completion
            transcoder.shutdown();
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("✓ Folder processing initiated successfully!");
            System.out.println("  Duration: " + duration + " ms (" + duration/1000.0 + " seconds)");
            System.out.println("  Note: Actual conversion may still be running in background");
            
        }
        catch (Exception e)
        {
            System.err.println("✗ Folder processing failed:");
            e.printStackTrace();
        }
        finally
        {
            // Ensure shutdown even if there's an error
            transcoder.shutdown();
        }
    }
}