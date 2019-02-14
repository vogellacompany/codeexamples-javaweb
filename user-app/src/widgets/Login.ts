import WidgetBase from '@dojo/framework/widget-core/WidgetBase';
import { v, w } from '@dojo/framework/widget-core/d';
import Button from '@dojo/widgets/button';
import TextInput from '@dojo/widgets/text-input';
import * as css from './styles/Login.m.css';

export default class Login extends WidgetBase {

    private _onSubmit(event: Event) {
        event.preventDefault();
    }

    private _onEmailInput(email:  string) {
	}

	private _onPasswordInput(password: string) {
	}

	protected render() {
        return v('div', { }, [
                v('form', {
                    onsubmit: this._onSubmit
                }, [
                    v('fieldset', { }, [
                        w(TextInput, {
                            key: 'email',
                            label: 'Email',
                            placeholder: 'Email',
                            type: 'email',
                            required: true,
                            onInput: this._onEmailInput
                        }),
                        w(TextInput, {
                            key: 'password',
                            label: 'Password',
                            placeholder: 'Password',
                            type: 'password',
                            required: true,
                            onInput: this._onPasswordInput
                        }),
                        w(Button, { }, [ 'Login' ])
                    ]),
                ])
        ]);
	}
}
