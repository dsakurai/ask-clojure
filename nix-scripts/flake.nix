{
  description = "A Nix flake";


  inputs.nixpkgs.url = "github:NixOS/nixpkgs";

  outputs = { self, nixpkgs }: {
    defaultPackage.x86_64-linux = let
      pkgs = import nixpkgs {
         system = "x86_64-linux";
         config = {
           allowUnfree = true;
         };
      };
    in pkgs.mkShell {
      buildInputs = with pkgs; [
        clojure
        gradle
        kotlin
      ];

      shellHook = ''
          clj -Sdeps '{:deps {com.bhauman/rebel-readline {:mvn/version "0.1.4"}}}' -M -m rebel-readline.main
        '';

    };
  };
  
}

