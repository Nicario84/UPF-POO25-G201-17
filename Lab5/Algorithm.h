
#ifndef __ALGORITHM__
#define __ALGORITHM__

#include "Dataset.h"
#include "Model.h"

class Algorithm {

protected:
    Model model;

public:
    Algorithm( int dim )
      : model( dim ) {
    }

    virtual ~Algorithm() {}

    // El método solve entrena el datsset y devuelve el modelo  entrenado
    virtual Model solve( const Dataset & data ) = 0;

};

#endif
