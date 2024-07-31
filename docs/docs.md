- Account
* instituicao (Adicionar instituicoes disponiveis)
* descricao
* tipo (Conta corrente, Conta poupanca, Carteira fisica, investimentos, outros) => adicionar tipo de conta
* cor da conta (Criar table de cores)
* saldo da conta

instituicoes:
  "ispb": "00000000",
  "name": "BCO DO BRASIL S.A.",
  "code": 1,
  "fullName": "Banco do Brasil S.A."

tipo:
  name
  id

cores:
  name
  id

user         => n cards
instituicao  => n account
account_type => n account
colors       => n account