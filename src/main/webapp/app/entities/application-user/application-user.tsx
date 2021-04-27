import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './application-user.reducer';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IApplicationUserProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ApplicationUser = (props: IApplicationUserProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { applicationUserList, match, loading } = props;
  return (
    <div>
      <h2 id="application-user-heading" data-cy="ApplicationUserHeading">
        <Translate contentKey="projetZymaApp.applicationUser.home.title">Application Users</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="projetZymaApp.applicationUser.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="projetZymaApp.applicationUser.home.createLabel">Create new Application User</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {applicationUserList && applicationUserList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="projetZymaApp.applicationUser.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="projetZymaApp.applicationUser.profilBanner">Profil Banner</Translate>
                </th>
                <th>
                  <Translate contentKey="projetZymaApp.applicationUser.profilBio">Profil Bio</Translate>
                </th>
                <th>
                  <Translate contentKey="projetZymaApp.applicationUser.internalUser">Internal User</Translate>
                </th>
                <th>
                  <Translate contentKey="projetZymaApp.applicationUser.friends">Friends</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {applicationUserList.map((applicationUser, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${applicationUser.id}`} color="link" size="sm">
                      {applicationUser.id}
                    </Button>
                  </td>
                  <td>{applicationUser.profilBanner}</td>
                  <td>{applicationUser.profilBio}</td>
                  <td>{applicationUser.internalUser ? applicationUser.internalUser.id : ''}</td>
                  <td>
                    {applicationUser.friends
                      ? applicationUser.friends.map((val, j) => (
                          <span key={j}>
                            <Link to={`application-user/${val.id}`}>{val.id}</Link>
                            {j === applicationUser.friends.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${applicationUser.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${applicationUser.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${applicationUser.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="projetZymaApp.applicationUser.home.notFound">No Application Users found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ applicationUser }: IRootState) => ({
  applicationUserList: applicationUser.entities,
  loading: applicationUser.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ApplicationUser);
