package za;

public class Driver {
    public static void main(String[] args) {
        int[][][] data = Perceptron.andData;
        double[] weights = Perceptron.INITIAL_WEIGHT;
        Driver driver = new Driver();
        Perceptron perceptron = new Perceptron();
        int epochNumber = 0;
        boolean errorFlag = true;
        double[] adjustedWeights = null;



        System.out.println("Not learned. Lets try!");
        int test = 1000;
        int errorsCnt = 0;
        while (test --> 0) {
            for (int x = 0; x < data.length; x++) {
                double weightedSum = perceptron.calculateWeightedSum(data[x][0], weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                int error = data[x][1][0] - result;
                if (error != 0) {
                    errorsCnt++;
                }
            }
        }
        printReport(errorsCnt);

        while (errorFlag) {
            driver.printHeading(epochNumber++);
            errorFlag = false;
            for (int x = 0; x < data.length; x++) {
                double weightedSum = perceptron.calculateWeightedSum(data[x][0], weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                int error = data[x][1][0] - result;
                if (error != 0) {
                    errorFlag = true;
                }
                adjustedWeights = perceptron.adjustWeights(data[x][0], weights, error);
                driver.printVector(data[x], weights, result, error, weightedSum, adjustedWeights);
                weights = adjustedWeights;
            }
        }

        System.out.println("Learned! [#"+epochNumber+"]!");
        int test1 = 1000;
        errorsCnt = 0;
        while (test1 --> 0) {
            for (int x = 0; x < data.length; x++) {
                double weightedSum = perceptron.calculateWeightedSum(data[x][0], weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                int error = data[x][1][0] - result;
                if (error != 0) {
                    errorsCnt++;
                }
            }
        }
        printReport(errorsCnt);
    }

    public static void printReport(int errors){

        if(errors>0){
            System.out.println("TEST FAILED ("+errors+" errors)");
        }else{
            System.out.println("TEST PASSED");
        }
    }

    public void printVector(int[][] data, double[] weights, int result, double error, double weightedSum, double[] adjustedWeights) {
        StringBuilder str = new StringBuilder();
        str.append(" ");
        str.append(String.format("%.2f", weights[0]));
        str.append("  | ");
        str.append(String.format("%.2f", weights[1]));
        str.append("  |  ");
        str.append(data[0][0]);
        str.append("    |  ");
        str.append(data[0][1]);
        str.append("    |        ");
        str.append(data[1][0]);
        str.append("       |    ");
        str.append(result);
        str.append("       |  ");
        str.append(error);
        str.append("     |        ");
        str.append(String.format("%.2f", weightedSum));
        str.append("     |     ");
        str.append(String.format("%.2f", adjustedWeights[0]));
        str.append("       |  ");
        str.append(String.format("%.2f", adjustedWeights[1]));
        str.append("         |");
        System.out.println(str.toString());
    }

    public void printHeading(int epochNumber) {
        StringBuilder str = new StringBuilder();
        str.append("\n============================================================Epoch #").append(epochNumber).append("========================================================\n");
        str.append("  w1   |  w2   |  x1   |  x2   |  TargetResult   |  Result   |  error   |  Weighted Sum   |  Adjusted w1   |  Adjusted w2  |");
        str.append("\n----------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(str.toString());
    }
}
