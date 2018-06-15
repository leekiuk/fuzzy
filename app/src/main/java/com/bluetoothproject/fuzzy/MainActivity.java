package com.bluetoothproject.fuzzy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fileName = getFilesDir()+"/tipper.fcl";
        FIS fis = FIS.load(fileName, true);
        if (fis == null) { // Error while loading?
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Show ruleset
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        functionBlock.setVariable("service", 3);
        functionBlock.setVariable("food", 7);

        // Evaluate
        functionBlock.evaluate();

        // Show output variable's chart
        Variable tip = functionBlock.getVariable("tip");
        //JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
        Gpr.debug("poor[service]: " + functionBlock.getVariable("service").getMembership("poor"));
        Toast.makeText(MainActivity.this, functionBlock.toString() ,Toast.LENGTH_LONG);
    }
}
