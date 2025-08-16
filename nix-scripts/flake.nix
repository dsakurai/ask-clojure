{
  description = "A Nix flake";


  inputs.nixpkgs.url = "github:NixOS/nixpkgs";

  outputs = { self, nixpkgs }: {
    defaultPackage.x86_64-linux = let
      pkgs = import nixpkgs {
         system = "x86_64-linux";
         config = {
           enableJavaFX = true; # Likely not necessary
           allowUnfree = true;
         };
      };
    in pkgs.mkShell {
    
      nativeBuildInputs = [ pkgs.autoPatchelfHook ];

      buildInputs = with pkgs; [
        clojure
        gradle
        kotlin
      ];

      shellHook = ''
          # For JavaFX
          export LD_LIBRARY_PATH=${pkgs.libGL}/lib:${pkgs.gtk3}/lib:${pkgs.glib.out}/lib:${pkgs.libGLU}/lib:${pkgs.xorg.libXtst}/lib:$LD_LIBRARY_PATH
          clj -Sdeps '{:deps {com.bhauman/rebel-readline {:mvn/version "0.1.4"}}}' -M -m rebel-readline.main
        '';

    };
  };
  
}

