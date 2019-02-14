import WidgetBase from '@dojo/framework/widget-core/WidgetBase';
import { v, w } from '@dojo/framework/widget-core/d';
import Button from '@dojo/widgets/button';
import TextInput from '@dojo/widgets/text-input';
import I18nMixin from '@dojo/framework/widget-core/mixins/I18n';
import i18n, { switchLocale, systemLocale } from '@dojo/framework/i18n/i18n';
import messageBundle from './nls/login';

import * as css from './styles/Login.m.css';

export interface LoginProperties {
    email: string;
	password: string;
	inProgress?: boolean;
    errors: string[];
	onEmailInput: (email: string) => void;
	onPasswordInput: (password: string) => void;
	onLogin: (login: object) => void;
}

export default class Login extends I18nMixin(WidgetBase)<LoginProperties> {

    private _onSubmit(event: Event) {
        event.preventDefault();
        this.properties.onLogin({});
    }

    private _onEmailInput(email: string) {
        this.properties.onEmailInput(email);
	}

	private _onPasswordInput(password: string) {
        this.properties.onPasswordInput(password);
    }
    
    private _switchLocale() {
        if(i18n.locale !== 'de') {
            switchLocale('de');
        } else {
            switchLocale(systemLocale);
        }
    }

	protected render() {
        const { messages } = this.localizeBundle(messageBundle);
        const { email, password, inProgress = false } = this.properties;

        return v('div', { classes: css.root }, [
                v('form', {
                    onsubmit: this._onSubmit
                }, [
                    v('fieldset', { }, [
                        w(TextInput, {
                            key: 'email',
                            label: messages.email,
                            placeholder: 'Email',
                            type: 'email',
                            required: true,
                            value: email,
                            onInput: this._onEmailInput
                        }),
                        w(TextInput, {
                            key: 'password',
                            label: messages.password,
                            placeholder: 'Password',
                            type: 'password',
                            required: true,
                            value: password,
                            onInput: this._onPasswordInput
                        }),
                        w(Button, { 
                            disabled: inProgress
                        }, [ messages.login ])
                    ]),
                ]),
                w(Button, {
                    onClick: this._switchLocale
                }, ['Switch locale'])
        ]);
	}
}
