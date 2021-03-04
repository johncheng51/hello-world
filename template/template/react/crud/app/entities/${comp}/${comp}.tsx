import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './${comp}.reducer';
import { I${ucomp} } from 'app/shared/model/${comp}.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface I${ucomp}Props extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ${ucomp} = (props: I${ucomp}Props) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `@@{paginationState.sort},@@{paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=@@{paginationState.activePage}&sort=@@{paginationState.sort},@@{paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`@@{props.location.pathname}@@{endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get('sort');
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const { ${comp}List, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="${comp}-heading">
        Addresses
        <Link to={`@@{match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Address
        </Link>
      </h2>
      <div className="table-responsive">
        {${comp}List && ${comp}List.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('address1')}>
                  Address 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('address2')}>
                  Address 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('city')}>
                  City <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('postcode')}>
                  Postcode <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('country')}>
                  Country <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Customer <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {${comp}List.map((${comp}, i) => (
                <tr key={`entity-@@{i}`}>
                  <td>
                    <Button tag={Link} to={`@@{match.url}/@@{${comp}.id}`} color="link" size="sm">
                      {${comp}.id}
                    </Button>
                  </td>
                  <td>{${comp}.address1}</td>
                  <td>{${comp}.address2}</td>
                  <td>{${comp}.city}</td>
                  <td>{${comp}.postcode}</td>
                  <td>{${comp}.country}</td>
                  <td>{${comp}.customer ? <Link to={`customer/@@{${comp}.customer.id}`}>{${comp}.customer.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`@@{match.url}/@@{${comp}.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`@@{match.url}/@@{${comp}.id}/edit?page=@@{paginationState.activePage}&sort=@@{paginationState.sort},@@{paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`@@{match.url}/@@{${comp}.id}/delete?page=@@{paginationState.activePage}&sort=@@{paginationState.sort},@@{paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Addresses found</div>
        )}
      </div>
      {props.totalItems ? (
        <div className={${comp}List && ${comp}List.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={props.totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

const mapStateToProps = ({ ${comp} }: IRootState) => ({
  ${comp}List: ${comp}.entities,
  loading: ${comp}.loading,
  totalItems: ${comp}.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(${ucomp});
