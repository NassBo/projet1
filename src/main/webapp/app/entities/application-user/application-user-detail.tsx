import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './application-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IApplicationUserDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ApplicationUserDetail = (props: IApplicationUserDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { applicationUserEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="applicationUserDetailsHeading">
          <Translate contentKey="projetZymaApp.applicationUser.detail.title">ApplicationUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.id}</dd>
          <dt>
            <span id="profilBanner">
              <Translate contentKey="projetZymaApp.applicationUser.profilBanner">Profil Banner</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.profilBanner}</dd>
          <dt>
            <span id="profilBio">
              <Translate contentKey="projetZymaApp.applicationUser.profilBio">Profil Bio</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.profilBio}</dd>
          <dt>
            <Translate contentKey="projetZymaApp.applicationUser.internalUser">Internal User</Translate>
          </dt>
          <dd>{applicationUserEntity.internalUser ? applicationUserEntity.internalUser.id : ''}</dd>
          <dt>
            <Translate contentKey="projetZymaApp.applicationUser.friends">Friends</Translate>
          </dt>
          <dd>
            {applicationUserEntity.friends
              ? applicationUserEntity.friends.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {applicationUserEntity.friends && i === applicationUserEntity.friends.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/application-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/application-user/${applicationUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ applicationUser }: IRootState) => ({
  applicationUserEntity: applicationUser.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ApplicationUserDetail);
