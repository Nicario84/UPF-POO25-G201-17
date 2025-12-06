
#ifndef __SUPERVISED_LEARNER__
#define __SUPERVISED_LEARNER__

#include "Algorithm.h"

class SupervisedLearner {

private:
    Algorithm * algorithm;
    Model model;

public:
    SupervisedLearner( Algorithm * alg, int modelDim )
      : algorithm( alg ), model( modelDim ) {
    }

    // Aquí entrenamos aumentado el input (añadiendo el término del bias)
    void train( const Dataset & data ) {
        // Creamos el dataset aumentado
        Dataset aug( data.getDim() + 1 );
        std::vector<Record> orig = data.getData();
        for ( const Record & r : orig ) {
            Vector augIn = r.getInput().augment();
            Record newR( augIn, r.getOutput() );
            aug.addRecord( newR );
        }

        model = algorithm->solve( aug );
    }

    double predict( const Vector & input ) const {
        return model.predict( input.augment() );
    }

    friend std::ostream & operator<<( std::ostream & os, SupervisedLearner & sl ) {
        return os << sl.model;
    }

};

#endif
