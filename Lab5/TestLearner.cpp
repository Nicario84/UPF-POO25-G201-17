
#include <iostream>
#include "Dataset.h"
#include "GradientDescent.h"
#include "StochasticGradientDescent.h"
#include "SupervisedLearner.h"

int main() {

    Dataset ds( 2 );
    ds.addRecord( Record( Vector( std::vector<double>{1.0, 2.0} ), 3*1.0 + 2*2.0 + 1 ) );
    ds.addRecord( Record( Vector( std::vector<double>{2.0, 0.5} ), 3*2.0 + 2*0.5 + 1 ) );
    ds.addRecord( Record( Vector( std::vector<double>{0.5, 4.0} ), 3*0.5 + 2*4.0 + 1 ) );
    ds.addRecord( Record( Vector( std::vector<double>{3.0, 1.0} ), 3*3.0 + 2*1.0 + 1 ) );
    ds.addRecord( Record( Vector( std::vector<double>{4.0, 3.0} ), 3*4.0 + 2*3.0 + 1 ) );

    std::cout << "Dataset:\n" << ds << "\n\n";

    // Descenso de gradiente, donde reservamos un espcio más para incluir el bias
    GradientDescent gd( ds.getDim() + 1, 0.01, 10000, 1e-6 );
    SupervisedLearner learner( &gd, ds.getDim() + 1 );

    learner.train( ds );

    std::cout << "Trained model parameters (w0..wn, last is bias):\n" << learner << "\n";

    // Testeamos la preedicción
    Vector testIn( std::vector<double>{2.0, 1.0} );
    double pred = learner.predict( testIn );
    double expected = 3*2.0 + 2*1.0 + 1;
    std::cout << "Test input: " << testIn << "\n";
    std::cout << "Prediction: " << pred << " expected: " << expected << "\n";

    // Probamos el stochastic gradient descent
    StochasticGradientDescent sgd( ds.getDim() + 1, 0.01, 5000, 1e-6, 2 );
    SupervisedLearner learner2( &sgd, ds.getDim() + 1 );
    learner2.train( ds );
    std::cout << "SGD trained parameters:\n" << learner2 << "\n";

    return 0;
}


