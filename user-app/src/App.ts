import WidgetBase from '@dojo/framework/widget-core/WidgetBase';
import { v, w } from '@dojo/framework/widget-core/d';
import Outlet from '@dojo/framework/routing/Outlet';
import I18nMixin from '@dojo/framework/widget-core/mixins/I18n';

import Menu from './widgets/Menu';
import Home from './widgets/Home';
import About from './widgets/About';
import Profile from './widgets/Profile';

import * as css from './App.m.css';
import { LoginProperties } from './widgets/Login';
import Login from './widgets/Login';

export default class App extends I18nMixin(WidgetBase) {

	private getLoginProperties() : LoginProperties {
		let _email = "simon.scholz@vogella.com"
		let _password = "super secret"
		let _inProgress = false;
		return {
			email: _email,
			password: _password,
			inProgress: _inProgress,
			errors: [''],
			onEmailInput: (email: string) => {_email = email},
			onPasswordInput: (password: string) => {_password = password},
			onLogin: (login: object) => {
				_inProgress = true;
				console.log("loging in...");
			}
		};
	}

	protected render() {
		return v('div', { classes: [css.root] }, [
			w(Menu, {}),
			v('div', [
				w(Outlet, { key: 'home', id: 'home', renderer: () => w(Home, {}) }),
				w(Outlet, { key: 'about', id: 'about', renderer: () => w(About, {}) }),
				w(Outlet, { key: 'profile', id: 'profile', renderer: () => w(Profile, { username: 'Simon Scholz' }) }),
				w(Outlet, { key: 'login', id: 'login', renderer: () => w(Login, this.getLoginProperties()) })
			])
		]);
	}
}
