package FileHandler;

import java.io.File;

public class DirectoryBrowserInteractiveTest
{
    public static void main(String[] args)
    {
        testScenarios();
    }

    private static void testScenarios()
    {
        System.out.println("=== DirectoryBrowser Test Scenarios ===\n");

        // Test 1: Normal selection
        System.out.println("Test 1: Please select a valid directory");
        testDirectorySelection("Test 1 - Select Valid Directory");

        // Test 2: Cancel action
        System.out.println("\nTest 2: Please click Cancel");
        testDirectorySelection("Test 2 - Cancel Test");

        // Test 3: Different title
        System.out.println("\nTest 3: Testing with different title");
        testDirectorySelection("Custom Title - Choose Your Folder");
    }

    private static void testDirectorySelection(String title)
    {
        File result = DirectoryBrowser.selectDirectory(title);

        if (result != null) {
            System.out.println("✓ Directory selected: " + result.getName());
            System.out.println("  Full path: " + result.getAbsolutePath());
            System.out.println("  Readable: " + result.canRead());
            System.out.println("  Writable: " + result.canWrite());
        } else {
            System.out.println("✗ No directory selected (cancelled)");
        }
    }
}