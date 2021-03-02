import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { getEntity, updateEntity, createEntity, reset } from './${comp}.reducer';
import { I${ucomp} } from 'app/shared/model/${comp}.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface I${ucomp}UpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ${ucomp}Update = (props: I${ucomp}UpdateProps) => {
  const [customerId, setCustomerId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ${comp}Entity, customers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/${comp}' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCustomers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...${comp}Entity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="reactApp.${comp}.home.createOrEditLabel">Create or edit a Address</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ${comp}Entity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="${comp}-id">ID</Label>
                  <AvInput id="${comp}-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="${comp}1Label" for="${comp}-address1">
                  Address 1
                </Label>
                <AvField id="${comp}-address1" type="text" name="address1" />
              </AvGroup>
              <AvGroup>
                <Label id="${comp}2Label" for="${comp}-address2">
                  Address 2
                </Label>
                <AvField id="${comp}-address2" type="text" name="address2" />
              </AvGroup>
              <AvGroup>
                <Label id="cityLabel" for="${comp}-city">
                  City
                </Label>
                <AvField id="${comp}-city" type="text" name="city" />
              </AvGroup>
              <AvGroup>
                <Label id="postcodeLabel" for="${comp}-postcode">
                  Postcode
                </Label>
                <AvField
                  id="${comp}-postcode"
                  type="text"
                  name="postcode"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="countryLabel" for="${comp}-country">
                  Country
                </Label>
                <AvField
                  id="${comp}-country"
                  type="text"
                  name="country"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 2, errorMessage: 'This field cannot be longer than 2 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="${comp}-customer">Customer</Label>
                <AvInput id="${comp}-customer" type="select" className="form-control" name="customer.id">
                  <option value="" key="0" />
                  {customers
                    ? customers.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/${comp}" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  customers: storeState.customer.entities,
  ${comp}Entity: storeState.${comp}.entity,
  loading: storeState.${comp}.loading,
  updating: storeState.${comp}.updating,
  updateSuccess: storeState.${comp}.updateSuccess,
});

const mapDispatchToProps = {
  getCustomers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(${ucomp}Update);
