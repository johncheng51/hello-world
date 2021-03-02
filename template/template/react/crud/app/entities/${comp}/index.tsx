import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import ${ucomp} from './${comp}';
import ${ucomp}Detail from './${comp}-detail';
import ${ucomp}Update from './${comp}-update';
import ${ucomp}DeleteDialog from './${comp}-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`@@{match.url}/:id/delete`} component={${ucomp}DeleteDialog} />
      <ErrorBoundaryRoute exact path={`@@{match.url}/new`} component={${ucomp}Update} />
      <ErrorBoundaryRoute exact path={`@@{match.url}/:id/edit`} component={${ucomp}Update} />
      <ErrorBoundaryRoute exact path={`@@{match.url}/:id`} component={${ucomp}Detail} />
      <ErrorBoundaryRoute path={match.url} component={${ucomp}} />
    </Switch>
  </>
);

export default Routes;
