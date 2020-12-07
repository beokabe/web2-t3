package br.com.ufms.web.trabalho.beatriz.dto;

import br.com.ufms.web.trabalho.beatriz.entity.Produto;
import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import br.com.ufms.web.trabalho.beatriz.service.LoginService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class LoginDTO {
    private String login;
    private String senha;

    public static LoginDTO buildDTO(Usuario usuario) {
        return new LoginDTO(usuario.getLogin(), usuario.getSenha());
    }
}
