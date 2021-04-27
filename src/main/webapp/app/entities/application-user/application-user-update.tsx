import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntities as getApplicationUsers } from 'app/entities/application-user/application-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './application-user.reducer';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IApplicationUserUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ApplicationUserUpdate = (props: IApplicationUserUpdateProps) => {
  const [idsfriends, setIdsfriends] = useState([]);
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { applicationUserEntity, users, applicationUsers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/application-user');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
    props.getApplicationUsers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...applicationUserEntity,
        ...values,
        friends: mapIdList(values.friends),
        internalUser: users.find(it => it.id.toString() === values.internalUserId.toString()),
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
          <h2 id="projetZymaApp.applicationUser.home.createOrEditLabel" data-cy="ApplicationUserCreateUpdateHeading">
            <Translate contentKey="projetZymaApp.applicationUser.home.createOrEditLabel">Create or edit a ApplicationUser</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : applicationUserEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="application-user-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="application-user-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="profilBannerLabel" for="application-user-profilBanner">
                  <Translate contentKey="projetZymaApp.applicationUser.profilBanner">Profil Banner</Translate>
                </Label>
                <AvField id="application-user-profilBanner" data-cy="profilBanner" type="text" name="profilBanner" />
              </AvGroup>
              <AvGroup>
                <Label id="profilBioLabel" for="application-user-profilBio">
                  <Translate contentKey="projetZymaApp.applicationUser.profilBio">Profil Bio</Translate>
                </Label>
                <AvField id="application-user-profilBio" data-cy="profilBio" type="text" name="profilBio" />
              </AvGroup>
              <AvGroup>
                <Label for="application-user-internalUser">
                  <Translate contentKey="projetZymaApp.applicationUser.internalUser">Internal User</Translate>
                </Label>
                <AvInput
                  id="application-user-internalUser"
                  data-cy="internalUser"
                  type="select"
                  className="form-control"
                  name="internalUserId"
                  required
                >
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>
                  <Translate contentKey="entity.validation.required">This field is required.</Translate>
                </AvFeedback>
              </AvGroup>
              <AvGroup>
                <Label for="application-user-friends">
                  <Translate contentKey="projetZymaApp.applicationUser.friends">Friends</Translate>
                </Label>
                <AvInput
                  id="application-user-friends"
                  data-cy="friends"
                  type="select"
                  multiple
                  className="form-control"
                  name="friends"
                  value={!isNew && applicationUserEntity.friends && applicationUserEntity.friends.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {applicationUsers
                    ? applicationUsers.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/application-user" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  applicationUsers: storeState.applicationUser.entities,
  applicationUserEntity: storeState.applicationUser.entity,
  loading: storeState.applicationUser.loading,
  updating: storeState.applicationUser.updating,
  updateSuccess: storeState.applicationUser.updateSuccess,
});

const mapDispatchToProps = {
  getUsers,
  getApplicationUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ApplicationUserUpdate);
