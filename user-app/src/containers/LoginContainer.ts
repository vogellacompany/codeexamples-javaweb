import { Container } from '@dojo/framework/widget-core/Container';
import Login, { LoginProperties } from '../widgets/Login';

let _email = "simon.scholz@vogella.com"
let _password = "super secret"
let _inProgress = false;

function getProperties() : LoginProperties {
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

export default Container(Login, 'app-state', { getProperties });