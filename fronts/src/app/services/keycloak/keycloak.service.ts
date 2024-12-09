import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js'
import { UserProfile } from 'src/app/model/user-profile';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak?: Keycloak;
  private profile?: UserProfile;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9080',
        realm: 'chat',
        clientId: 'chat-be'
      })
    }
    return this._keycloak;
  }

  constructor() { }

  async init() {
    const authenticated = await this.keycloak?.init({
      onLoad: 'login-required'
    })
    if (authenticated) {
      this.profile = await this.keycloak.loadUserProfile() as UserProfile;
      this.profile.token = this.keycloak.token;
    }
  }

  login() {
    return this.keycloak.login()
  }

  logout() {
    return this.keycloak.logout({redirectUri: 'http://localhost:4200'})
  }
}
