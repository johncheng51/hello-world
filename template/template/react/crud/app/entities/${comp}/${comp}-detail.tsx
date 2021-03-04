import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './${comp}.reducer';
import { I${ucomp} } from 'app/shared/model/${comp}.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface I${ucomp}DetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ${ucomp}Detail = (props: I${ucomp}DetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ${comp}Entity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Address [<b>{${comp}Entity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="address1">Address 1</span>
          </dt>
          <dd>{${comp}Entity.address1}</dd>
          <dt>
            <span id="address2">Address 2</span>
          </dt>
          <dd>{${comp}Entity.address2}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{${comp}Entity.city}</dd>
          <dt>
            <span id="postcode">Postcode</span>
          </dt>
          <dd>{${comp}Entity.postcode}</dd>
          <dt>
            <span id="country">Country</span>
          </dt>
          <dd>{${comp}Entity.country}</dd>
          <dt>Customer</dt>
          <dd>{${comp}Entity.customer ? ${comp}Entity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/${comp}" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/${comp}/@@{${comp}Entity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ${comp} }: IRootState) => ({
  ${comp}Entity: ${comp}.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(${ucomp}Detail);
